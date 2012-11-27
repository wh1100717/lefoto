/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.cache;

import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.user.UserService;
import java.util.HashMap;
import java.util.List;
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
    Map<String, Map<String,Object>> usersMap = new HashMap<String, Map<String,Object>>();

    public void initUserMap() {
        List<LeUser> users = userService.findAllUsers();
        for(int index=0; index < users.size(); index++){
            LeUser user = users.get(index);
            Map<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("bean", user);
            usersMap.put(String.valueOf(user.getId()), userMap);
        }
    }
    public LeUser getUserById(int id){
        return (LeUser) usersMap.get(String.valueOf(id)).get("bean");
    }
    public void updateUserBean(LeUser user){
        usersMap.get(String.valueOf(user.getId())).put("bean", user);
    }
}
