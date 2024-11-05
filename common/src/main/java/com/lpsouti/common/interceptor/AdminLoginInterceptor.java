package com.lpsouti.common.interceptor;

import com.lpsouti.common.constant.Role;
import com.lpsouti.common.mapper.UserMapper;
import com.lpsouti.common.utils.RedisKeyUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class AdminLoginInterceptor implements HandlerInterceptor {

    private final UserMapper userMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String path = request.getRequestURI();
        log.debug("请求方式：{}，请求路径：{}", method, path);

        // 排除登录接口
        if ("GET".equals(method) && "/user/login".equals(path)) {
            return true;
        }
        // 排除添加用户接口(系统无用户时)
        if ("POST".equals(method) && "/user".equals(path) && !userMapper.exists()) {
            return true;
        }

        // 校验请求头
        String token = request.getHeader("Authorization");
        log.debug("登录token：{}", token);
        if (StringUtils.isBlank(token)) {
            // token为空，视为未登录
            response.setStatus(401);
            return false;
        }

        // 查询redis
        List<String> list = redisTemplate.<String, String>opsForHash().multiGet(
                RedisKeyUtil.loginInfo(token),
                RedisKeyUtil.LOGIN_INFO_ID_ROLE
        );
        log.debug("redis中的登录信息：{}", list);
        String id = list.get(0);
        String role = list.get(1);
        if (StringUtils.isBlank(id) || StringUtils.isBlank(role)) {
            response.setStatus(401);
            return false;
        }

        // 判断是否有登录权限
        if (!role.equals("" + Role.ADMIN)) {
            response.setStatus(403);
            return false;
        }

        // 放行
        return true;
    }
}
