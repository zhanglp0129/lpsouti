package com.lpsouti.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class RedisKeyUtil {
    private RedisKeyUtil() {}

    public static String loginInfo(String token) {
        String key = String.format("login-info:%s", token);
        log.debug("login info redis key: {}", key);
        return key;
    }

    // 登录信息相关redis hash key
    public static final String LOGIN_INFO_ID = "id";
    public static final String LOGIN_INFO_ROLE = "role";
    public static final List<String> LOGIN_INFO_ID_ROLE = Arrays.asList(LOGIN_INFO_ID, LOGIN_INFO_ROLE);
}
