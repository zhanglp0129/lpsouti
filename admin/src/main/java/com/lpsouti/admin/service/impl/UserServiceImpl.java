package com.lpsouti.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lpsouti.admin.dto.LoginDTO;
import com.lpsouti.admin.dto.UserAddDTO;
import com.lpsouti.admin.service.UserService;
import com.lpsouti.admin.vo.LoginVO;
import com.lpsouti.common.constant.ErrorCode;
import com.lpsouti.common.constant.Role;
import com.lpsouti.common.entity.Balance;
import com.lpsouti.common.entity.User;
import com.lpsouti.common.entity.UserInfo;
import com.lpsouti.common.entity.redis.LoginInfo;
import com.lpsouti.common.exception.CommonException;
import com.lpsouti.common.mapper.BalanceMapper;
import com.lpsouti.common.mapper.UserInfoMapper;
import com.lpsouti.common.mapper.UserMapper;
import com.lpsouti.common.properties.LoginProperties;
import com.lpsouti.common.utils.RedisKeyUtil;
import com.lpsouti.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

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
        if (!StringUtils.isEmpty(loginDTO.getUsername())) {
            // 用户名登录
            wrapper.eq(User::getUsername, loginDTO.getUsername());
        } else if (!StringUtils.isEmpty(loginDTO.getEmail())) {
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
        if (user.getRole() != Role.ADMIN) {
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
}
