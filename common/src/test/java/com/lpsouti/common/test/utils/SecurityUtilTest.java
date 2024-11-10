package com.lpsouti.common.test.utils;

import com.lpsouti.common.utils.SecurityUtil;
import org.junit.jupiter.api.Test;

public class SecurityUtilTest {

    @Test
    public void testEncryptPassword() {
        String password = "123456";
        String salt = "lpsouti666";
        String encrypted = SecurityUtil.encryptPassword(password, salt);
        assert encrypted.equals("85de6bd64d917459aacd8d31f8f7d895");
    }

    @Test
    public void testGenerateSecret() {
        Long userId = 123L;
        String secretId = SecurityUtil.generateSecretId(userId);
        String secretKey = SecurityUtil.generateSecretKey(userId);
        System.out.println("secretId = " + secretId);
        System.out.println("secretKey = " + secretKey);
    }

    @Test
    public void testGenerateSalt() {
        String salt = SecurityUtil.generateSalt();
        System.out.println("salt = " + salt);
    }

    @Test
    public void testGenerateLoginToken() {
        Long userId = 123L;
        String token = SecurityUtil.generateLoginToken(userId);
        System.out.println("token = " + token);
    }
}
