package com.example.dmp.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName:    MD532Utils
 * @Description:  TODO
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 17:14
 * @Version:      V1.0
 * @Since:        V1.0
 */
public class MD532Utils {
    /**
     * 获取32位小写的MD5加密
     * @param str
     * @return
     */
    public static String toMd532LowerString(String str) {
        String result = "";
        MessageDigest md5 = null;
        StringBuffer buf = getStringBuffer(str);
        result = buf.toString();
        return  result;
    }

    private static StringBuffer getStringBuffer(String str) {
        MessageDigest md5;
        StringBuffer buf = new StringBuffer("");
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update((str).getBytes("UTF-8"));
            byte b[] = md5.digest();
            int i;
            for (
                    int offset = 0;
                    offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return buf;
    }

    /**
     * 获取32位大写的MD5加密
     * @param str
     * @return
     */
    public static String toMd532UpperCaseString(String str) {
        String result = "";
        MessageDigest md5 = null;
        StringBuffer buf = getStringBuffer(str);
        result = buf.toString().toUpperCase();
        return  result;
    }
}
