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
import com.lpsouti.admin.mapper.LoginRecordMapper;
import com.lpsouti.admin.mapper.UserInfoMapper;
import com.lpsouti.admin.mapper.UserMapper;
import com.lpsouti.admin.service.UserService;
import com.lpsouti.admin.vo.user.LoginVO;
import com.lpsouti.admin.vo.user.UserVO;
import com.lpsouti.common.constant.ErrorCode;
import com.lpsouti.common.constant.Role;
import com.lpsouti.common.constant.UserStatus;
import com.lpsouti.common.entity.Balance;
import com.lpsouti.common.entity.LoginRecord;
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

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;
    private final BalanceMapper balanceMapper;
    private final LoginRecordMapper loginRecordMapper;
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
    public LoginVO login(LoginDTO loginDTO, String userAgent, String ip) {
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
                // 创建登录记录对象
                LoginRecord loginRecord = new LoginRecord();
                loginRecord.setUserId(user.getId());
                loginRecord.setToken(token);
                loginRecord.setIp(ip);
                loginRecord.setUserAgent(userAgent);
                loginRecord.setRole(Role.ADMIN);
                loginRecord.setExpireTime(LocalDateTime.now().plusSeconds(loginProperties.getExpireSeconds()));
                log.debug("login record = {}", loginRecord);
                // 添加一条登录记录
                loginRecordMapper.insert(loginRecord);
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
        IPage<UserVO> page = dto.toIPage();
        page = userMapper.pageQuery(page, dto);

        // 封装返回结果
        PageVO<UserVO> vo = PageVO.fromIPage(page);
        log.debug("user page vo = {}", vo);
        return vo;
    }

    @Override
    public UserVO queryById(Long id) {
        UserVO vo = userMapper.queryById(id);
        if (vo==null) {
            throw new CommonException("该用户不存在");
        }
        return vo;
    }

    @Override
    public void editStatus(Long id, Byte status, Boolean offline) {
        // 构造修改实体
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        // 修改数据
        int rows = userMapper.updateById(user);
        if (rows == 0) {
            throw new CommonException("修改用户状态失败");
        }

        if (UserStatus.BANNED.equals(status) && offline) {
            // TODO 强制下线：删除redis中所有该用户的登录token
        }
    }

    @Override
    @Transactional
    public void delete(Long id, Boolean offline) {
        // 构造删除条件
        LambdaQueryWrapper<UserInfo> infoWrapper = new LambdaQueryWrapper<>();
        infoWrapper.eq(UserInfo::getUserId, id);
        LambdaQueryWrapper<Balance> balanceWrapper = new LambdaQueryWrapper<>();
        balanceWrapper.eq(Balance::getUserId, id);
        // 删除数据
        int rows = userMapper.deleteById(id);
        rows += userInfoMapper.delete(infoWrapper);
        rows += balanceMapper.delete(balanceWrapper);
        // 没有删除数据
        if (rows == 0) {
            throw new CommonException("删除用户失败");
        }

        if (offline) {
            // TODO 强制下线：删除redis中所有该用户的登录token
        }
    }

    @Override
    public void editBalance(Long id, BigDecimal balance, BigDecimal freeBalance) {
        // 构造修改条件
        LambdaUpdateWrapper<Balance> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Balance::getUserId, id)
                .set(balance!=null, Balance::getBalance, balance)
                .set(freeBalance!=null, Balance::getFreeBalance, freeBalance);
        // 修改数据
        int rows = balanceMapper.update(wrapper);
        if (rows==0) {
            throw new CommonException("修改余额失败");
        }
    }

    @Override
    public List<UserVO> queryBatch(List<Long> ids) {
        List<UserVO> vos = userMapper.queryBatch(ids);
        log.debug("user batch query vo = {}", vos);
        return vos;
    }
}
