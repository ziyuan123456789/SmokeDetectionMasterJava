package com.example.chapter6_wzy.Utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * @author wsh
 */
public class Md5 {
    public static String clean(String text) {
        MessageDigest digest = null;
        try {
            // 创建加密对象
            digest = MessageDigest.getInstance("md5");
            // 数组 byte[] result -> digest.digest( ); 文本 text.getBytes();
            // 调用加密对象，加密动作已完成
            byte[] result = digest.digest(text.getBytes());
            // 创建StringBuilder对象 然后建议StringBuffer，安全性高，
            // StringBuilder 相较于 StringBuffer 有速度优势；StringBuffer 线程安全
            // 接下来对加密后的结果进行优化
            StringBuffer sb = new StringBuffer();
            // result数组，digest.digest ( ); -> text.getBytes();
            // for 循环数组byte[] result;
            for (byte b : result) {
                // 将数据全部转换为正数
                int number = b & 0xff;// 也就是255
                // 解释：为什么采用b&255
                /*
                 * b:它本来是一个byte类型的数据(1个字节) 255：是一个int类型的数据(4个字节)
                 * byte类型的数据与int类型的数据进行运算，会自动类型提升为int类型 eg: b: 1001 1100(原始数据)
                 * 运算时：
                 * b : 0000 0000 0000 0000 0000 0000 1001 1100
                 * 255: 0000 0000 0000 0000 0000 0000 1111 1111
                 * 结果：0000 0000 0000 0000 0000 0000 1001 1100
                 * 此时的temp是一个int类型的整数
                 */
                // 将所有的数据转换成16进制的形式
                String hex = Integer.toHexString(number);
                // 当正数小于16时，使用Integer.toHexString(number)可能会造成缺少位数
                if (number < 16 && number >= 0) {
                    // 手动补上一个“0”
                    sb.append("0" + hex);
                } else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // 发送异常return空字符串
            return "";
        }

    }


}