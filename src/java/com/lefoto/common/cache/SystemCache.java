/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.cache;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Eric
 */
public class SystemCache {
    
    static String currentSystemPath = "";
    static public void initSystemCache(HttpServletRequest request){
        currentSystemPath = request.getSession().getServletContext().getRealPath("/");
        System.out.println("系统当前路径："+ currentSystemPath);
    }
    static public String getCurrentSystemPath(){
        return currentSystemPath;
    }
}
