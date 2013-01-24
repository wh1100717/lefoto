/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Eric
 */
public class FormatUtil {

    static public String trimAll(String input) {
        String dest = "";
        if (input != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(input);
            dest = m.replaceAll("");
        }
        return dest;
        /*-----------------------------------
         笨方法：String s = "你要去除的字符串";
         1.去除空格：s = s.replace('\\s','');
         2.去除回车：s = s.replace('\n','');
         这样也可以把空格和回车去掉，其他也可以照这样做。
         注：\n 回车(\u000a) 
         \t 水平制表符(\u0009) 
         \s 空格(\u0008) 
         \r 换行(\u000d)
         */
    }

    static public int getUrlTag(String url) {
        int checkChar = 1;
        char[] charSet = url.toCharArray();
        int length = charSet.length;
        if (length >= 6) {
            checkChar = charSet[length - 6];
        }
        return checkChar % 3 + 1;
    }

    static public boolean checkEmailFormat(String email) {
        email = trimAll(email);
        String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean result = matcher.find();
        return result;
    }

    static public String checkPasswordFormat(String password, String passconf) {
        //如果password和passconf不相同，则返回两次输入的密码不一致
        if (!password.trim().equals(passconf.trim())) {
            return "passconfError";
        }
        //简单判断用户密码是否大于六位
        if (password.length() >= 6) {
            return "right";
        } else {
            return "lengthError";
        }
    }
}