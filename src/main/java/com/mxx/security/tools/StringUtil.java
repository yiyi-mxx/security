package com.mxx.security.tools;


import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Component
public class StringUtil {
    public static boolean isEmpty(String s){
        s=s.trim();
        if(s.equals("")||s.isEmpty()||s==null){
            return true;
        }else {
            return false;
        }
    }
    public static String MD5(String param) {
        try {
            byte[] byteArray = param.getBytes("utf-8");
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuilder builder = new StringBuilder();
            for (byte md5Byte : md5Bytes) {
                int val = ((int) md5Byte) & 0xff;
                if (val < 16) {
                    builder.append("0");
                }
                builder.append(Integer.toHexString(val));
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 生成？位的数字类型的短信验证码
     * @param count
     * @return
     */
    public static String generatedCode(int count) {
        List<Integer> set = getRandomNumber(count);
        // 使用迭代器
        Iterator<Integer> iterator = set.iterator();
        // 临时记录数据
        String temp = "";
        while (iterator.hasNext()) {
            temp += iterator.next();

        }
        return temp;
    }
    private static List<Integer> getRandomNumber(int count) {
        // 使用SET以此保证写入的数据不重复
        List<Integer> set = new ArrayList<Integer>();
        // 随机数
        Random random = new Random();

        while (set.size() < count) {
            // nextInt返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）
            // 和指定值（不包括）之间均匀分布的 int 值。
            set.add(random.nextInt(10));
        }
        return set;
    }
}
