package com.yy.bjtours.modules.sys.utils;

import java.util.Random;

/**
 * RandomCodeUtils
 *
 * @author lufl
 * @date 2016/4/28
 */
public class RandomCodeUtils {
    static String source = "0123456789";
    static char[] src = source.toCharArray();

    private static String randomStr(int length) {
        Random random = new Random();
        char[] srcChars = source.toCharArray();
        char[] buf = new char[length];
        for (int i = 0; i < length; i++) {
            int rand = Math.abs(random.nextInt()) % srcChars.length;
            buf[i] = src[rand];
        }
        return new String(buf);
    }
    public static void main(String[] args) {
    	System.out.println(generateVerifyCode(6));
    	
	}

    public static String generateVerifyCode(int length) {
        String verify = randomStr(length);
        return verify;
    }
}
