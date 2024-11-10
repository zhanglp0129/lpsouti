package com.lpsouti.admin.initializer;

import com.lpsouti.admin.dto.user.UserAddDTO;
import com.lpsouti.admin.mapper.UserMapper;
import com.lpsouti.admin.service.UserService;
import com.lpsouti.common.constant.Role;
import com.lpsouti.common.properties.DefaultUserProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultUserInitializer implements CommandLineRunner {

    private final UserService userService;
    private final UserMapper userMapper;
    private final DefaultUserProperties defaultUserProperties;

    @Override
    public void run(String... args) {
        // 通过系统中是否存在用户，判断是否初始化默认用户
        if (userMapper.exists()) {
            return;
        }

        // 直接复用添加用户的代码
        UserAddDTO dto = new UserAddDTO();
        dto.setUsername(defaultUserProperties.getUsername());
        dto.setEmail(defaultUserProperties.getEmail());
        dto.setPassword(defaultUserProperties.getPassword());
        dto.setRole(Role.ADMIN);
        log.info("user dto = {}", dto);
        userService.add(dto);
    }
}
