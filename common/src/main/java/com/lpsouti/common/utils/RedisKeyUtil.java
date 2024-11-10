package com.lpsouti.common.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisKeyUtil {
    private RedisKeyUtil() {
    }

    // 登录token的redis key
    public static String login(String token) {
        String key = String.format("login:%s", token);
        log.debug("login redis key: {}", key);
        return key;
    }

}
