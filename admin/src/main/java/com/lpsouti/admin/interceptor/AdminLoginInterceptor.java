package com.lpsouti.admin.interceptor;

import cn.hutool.core.util.StrUtil;
import com.lpsouti.common.constant.Role;
import com.lpsouti.common.entity.redis.LoginInfo;
import com.lpsouti.common.utils.RedisKeyUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
@RequiredArgsConstructor
@Slf4j
public class AdminLoginInterceptor implements HandlerInterceptor {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断是否位于虚拟线程
        log.debug("位于虚拟线程：{}", Thread.currentThread().isVirtual());
        // 获取请求方式和路径
        String method = request.getMethod();
        String path = request.getRequestURI();
        log.debug("请求方式：{}，请求路径：{}", method, path);

        // 排除登录接口
        if ("POST".equals(method) && "/user/login".equals(path)) {
            return true;
        }

        // 校验请求头
        String token = request.getHeader("Authorization");
        log.debug("登录token：{}", token);
        if (StrUtil.isBlank(token)) {
            // token为空，视为未登录
            response.setStatus(401);
            return false;
        }

        // 查询redis
        String key = RedisKeyUtil.login(token);
        LoginInfo loginInfo = (LoginInfo) redisTemplate.opsForValue().get(key);
        log.debug("redis中的登录信息：{}", loginInfo);
        if (loginInfo == null || loginInfo.getId() == null || loginInfo.getRole() == null) {
            response.setStatus(401);
            return false;
        }

        // 判断是否有管理员权限
        if (!loginInfo.getRole().equals(Role.ADMIN)) {
            response.setStatus(403);
            return false;
        }

        // 放行
        return true;
    }
}
