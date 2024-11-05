package com.lpsouti.admin.service.impl;

import com.lpsouti.admin.dto.UserAddDTO;
import com.lpsouti.admin.service.UserService;
import com.lpsouti.common.entity.Balance;
import com.lpsouti.common.entity.User;
import com.lpsouti.common.entity.UserInfo;
import com.lpsouti.common.mapper.BalanceMapper;
import com.lpsouti.common.mapper.UserInfoMapper;
import com.lpsouti.common.mapper.UserMapper;
import com.lpsouti.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;
    private final BalanceMapper balanceMapper;

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
}
