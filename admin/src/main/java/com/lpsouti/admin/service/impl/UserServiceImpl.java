package com.lpsouti.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lpsouti.admin.dto.user.LoginDTO;
import com.lpsouti.admin.dto.user.UserAddDTO;
import com.lpsouti.admin.dto.user.UserEditDTO;
import com.lpsouti.admin.dto.user.UserPageDTO;
import com.lpsouti.admin.mapper.BalanceMapper;
import com.lpsouti.admin.mapper.UserInfoMapper;
import com.lpsouti.admin.mapper.UserMapper;
import com.lpsouti.admin.service.UserService;
import com.lpsouti.admin.vo.user.LoginVO;
import com.lpsouti.admin.vo.user.UserVO;
import com.lpsouti.common.constant.ErrorCode;
import com.lpsouti.common.constant.Role;
import com.lpsouti.common.entity.Balance;
import com.lpsouti.common.entity.User;
import com.lpsouti.common.entity.UserInfo;
import com.lpsouti.common.entity.redis.LoginInfo;
import com.lpsouti.common.exception.CommonException;
import com.lpsouti.common.properties.LoginProperties;
import com.lpsouti.common.utils.RedisKeyUtil;
import com.lpsouti.common.utils.SecurityUtil;
import com.lpsouti.common.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;
    private final BalanceMapper balanceMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final LoginProperties loginProperties;

    @Override
    @Transactional
    public void add(UserAddDTO userAddDTO) {
        // 生成盐值
        String salt = SecurityUtil.generateSalt();
        // 加密密码
        String encrypted = SecurityUtil.encryptPassword(userAddDTO.getPassword(), salt);

        // 创建数据库实体
        User user = new User();
        UserInfo userInfo = new UserInfo();
        Balance balance = new Balance();
        BeanUtils.copyProperties(userAddDTO, user);
        BeanUtils.copyProperties(userAddDTO, userInfo);
        user.setPassword(encrypted);
        user.setSalt(salt);
        log.debug("user = {}", user);
        log.debug("userInfo = {}", userInfo);
        log.debug("balance = {}", balance);

        // 插入数据
        userMapper.insert(user);
        userInfo.setUserId(user.getId());
        balance.setUserId(user.getId());
        userInfoMapper.insert(userInfo);
        balanceMapper.insert(balance);
    }

    @Override
    public LoginVO login(LoginDTO loginDTO) {
        // 查找用户。根据用户名查找，用户名为空则根据邮箱查找
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(loginDTO.getUsername())) {
            // 用户名登录
            wrapper.eq(User::getUsername, loginDTO.getUsername());
        } else if (StrUtil.isNotBlank(loginDTO.getEmail())) {
            // 邮箱登录
            wrapper.eq(User::getEmail, loginDTO.getEmail());
        } else {
            throw new CommonException(ErrorCode.BIND_ERROR_CODE, "用户名或邮箱为空");
        }
        User user = userMapper.selectOne(wrapper);
        log.debug("user = {}", user);

        // 用户不存在
        if (user==null) {
            throw new CommonException("用户名、邮箱或密码错误");
        }
        // 密码错误
        String encrypted = SecurityUtil.encryptPassword(loginDTO.getPassword(), user.getSalt());
        if (!encrypted.equals(user.getPassword())) {
            throw new CommonException("用户名、邮箱或密码错误");
        }
        // 无登录权限
        if (!Objects.equals(user.getRole(), Role.ADMIN)) {
            throw new CommonException("无登录权限");
        }

        // 将token写入redis，重复则重试
        for (int i=0;i<3;i++) {
            String token = SecurityUtil.generateLoginToken(user.getId());
            String key = RedisKeyUtil.login(token);
            LoginInfo loginInfo = new LoginInfo(user.getId(), Role.ADMIN);
            Boolean success = redisTemplate.opsForValue().setIfAbsent(key, loginInfo, Duration.ofSeconds(loginProperties.getExpireSeconds()));
            if (Boolean.TRUE.equals(success)) {
                // TODO 添加一条登录记录
                return new LoginVO(user.getId(), token);
            }
        }
        // 写入redis重试次数过多，抛出系统异常
        throw new RuntimeException("登陆失败：系统错误，请联系管理员");
    }

    @Override
    public Boolean exists() {
        return userMapper.exists();
    }

    @Override
    @Transactional
    public void edit(UserEditDTO userEditDTO) {
        // 创建数据库实体
        User user = new User();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userEditDTO, user);
        BeanUtils.copyProperties(userEditDTO, userInfo);

        if (userEditDTO.getPassword() != null) {
            // 生成盐值
            String salt = SecurityUtil.generateSalt();
            // 加密密码
            String encrypted = SecurityUtil.encryptPassword(userEditDTO.getPassword(), salt);
            user.setPassword(encrypted);
            user.setSalt(salt);
        }
        log.debug("user = {}", user);
        log.debug("userInfo = {}", userInfo);

        // 更新
        userMapper.updateById(user);
        LambdaUpdateWrapper<UserInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserInfo::getUserId, userEditDTO.getId());
        userInfoMapper.update(userInfo, wrapper);
    }

    @Override
    public PageVO<UserVO> pageQuery(UserPageDTO dto) {
        // 分页查询
        IPage<UserVO> page = dto.toIPage(dto.getOrderBy());
        page = userMapper.pageQuery(page, dto);

        // 封装返回结果
        PageVO<UserVO> vo = PageVO.fromIPage(page);
        log.debug("user page vo = {}", vo);
        return vo;
    }

    @Override
    public UserVO queryById(Long id) {
        return userMapper.queryById(id);
    }
}
