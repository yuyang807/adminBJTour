package com.yy.bjtours.common.utils;

import com.yy.bjtours.common.security.Digests;

/**
 * Created by user on 2016/1/11.
 */
public class PasswordUtils {

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     * @param plainPassword 明文密码
     * @param password 密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        byte[] salt = Encodes.decodeHex(password.substring(0,16));
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
    }

    public static void main(String[] args) {
        String str = "123456";
        String entryStr = entryptPassword(str);
        System.out.println("####" + entryStr);
        System.out.print(validatePassword(str, "57cf607fa35d7922c69dbab218779df9b0731f110de4cd0beb909d16"));
        String str1 = "00000000";
        String entryStr1 = entryptPassword(str1);
        System.out.println("####" + entryStr1);
        System.out.println("####" + entryStr1.length());
        System.out.print(validatePassword(str1, "342eef6c2c6027322fa9f566c630ce43ea2026f082d4302c20e9b938"));
    }
}
