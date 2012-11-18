/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.utils;

import java.util.Random;

/**
 *
 * @author Eric
 */
public class StringUtil {
    
    /**
     * 生成随机字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   //生成字符串从此序列中取
    Random random = new Random();   
    StringBuffer sb = new StringBuffer();   
    for (int i = 0; i < length; i++) {   
        int number = random.nextInt(base.length());   
        sb.append(base.charAt(number));   
    }   
    return sb.toString();   
 } 
}
