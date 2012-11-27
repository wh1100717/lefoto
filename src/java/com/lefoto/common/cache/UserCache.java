/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.cache;

import com.lefoto.service.iface.user.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户缓存类
 *
 * @author Eric
 */
public class UserCache {

    @Autowired
    UserService userService;
    Map<String, Map> userMap = new HashMap<String, Map>();

    public static void synUserMap() {
    }
}
