/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.utils;

/**
 *
 * @author Eric
 */
public class AuthenUtil {

    public static boolean refererAuthen(String referer) {
        if(referer == null){return false;}
        if (referer.contains("lefoto")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean userAgentAuthen(String userAgent){
        if(userAgent == null){return false;}
        if(userAgent.contains("Chrome")){
            return true;
        }
        if(userAgent.contains("Safari")){
            return true;
        }
        if(userAgent.contains("AppleWebKit")){
            return true;
        }
        if(userAgent.contains("Mozilla")){
            return true;
        }
        return false;
    }
}
