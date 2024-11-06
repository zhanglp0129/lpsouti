package com.lpsouti.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class RedisKeyUtil {
    private RedisKeyUtil() {}

    public static String login(String token) {
        String key = String.format("login:%s", token);
        log.debug("login redis key: {}", key);
        return key;
    }

}
