package com.lpsouti.common.utils;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 安全性相关工具类，主要包括：加密、生成盐值、生成api key等
 */
public class SecurityUtil {
    private SecurityUtil() {
    }

    /**
     * 加密密码
     * 加密方式为：md5(md5(password)+salt) 然后转为16进制
     *
     * @param password 密码明文
     * @param salt     盐值
     * @return 密码密文
     */
    public static String encryptPassword(@NotEmpty String password, @NotEmpty String salt) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 第一次md5
            String temp1 = toHexString(md5.digest(password.getBytes()));
            // 与盐值拼接
            String temp2 = temp1 + salt;
            // 第二次md5
            byte[] result = md5.digest(temp2.getBytes());
            return toHexString(result);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // 加密密钥。与加密密码相同
    public static String encryptSecretKey(@NotEmpty String secretKey, @NotEmpty String salt) {
        return encryptPassword(secretKey, salt);
    }

    /**
     * 生成密钥id
     * 生成方式为：sha256(userId+时间戳+随机数) 然后转为36进制，长度 [42, 50]
     *
     * @param userId 用户id
     * @return 密钥id
     */
    public static String generateSecretId(@NotNull Long userId) {
        return generateSecret(userId, true, 42);
    }

    /**
     * 生成密钥
     * 生成方式为：sha256(随机数+时间戳+userId) 然后转为36进制，长度 [42, 50]
     *
     * @param userId 用户id
     * @return 密钥
     */
    public static String generateSecretKey(@NotNull Long userId) {
        return generateSecret(userId, false, 42);
    }

    // 生成secretId或secretKey
    private static String generateSecret(Long userId, boolean isSecretId, int minLength) {
        // 准备参数
        String user = userId.toString();
        String timestamp = "" + System.currentTimeMillis();
        Random random = new Random();
        String rand = "" + random.nextLong();
        byte[] input = null;
        if (isSecretId) {
            input = (user + timestamp + rand).getBytes();
        } else {
            input = (rand + timestamp + user).getBytes();
        }

        // 求sha256
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            String result = toBase36String(sha256.digest(input));
            if (result.length() < minLength) {
                generateSecret(userId, isSecretId, minLength);
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成加密盐值。10个非空格的可打印字符
     *
     * @return 加密盐值
     */
    public static String generateSalt() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            builder.append((char) (random.nextInt(94) + 33));
        }
        return builder.toString();
    }

    /**
     * 生成登录token
     * 生成方式为：secretId+secretKey，长度 [90, 100]
     *
     * @param userId 用户id
     * @return 登录token
     */
    public static String generateLoginToken(@NotNull Long userId) {
        String token = generateSecretId(userId) + generateSecretKey(userId);
        if (token.length() < 90) {
            return generateLoginToken(userId);
        }
        return token;
    }

    // 将字节数组转为16进制字符串
    private static String toHexString(byte[] input) {
        StringBuilder builder = new StringBuilder(input.length * 2);
        for (byte b : input) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    // 将字节数组转为36进制字符串
    private static String toBase36String(byte[] input) {
        BigInteger integer = new BigInteger(1, input);
        return integer.toString(36);
    }
}
