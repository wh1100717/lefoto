/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.cache;

import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.user.UserService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 用户缓存类
 *
 * @author Eric
 */
public class UserCache {

    static Map<String, Map<String,Object>> usersMap = new HashMap<String, Map<String,Object>>();

    static public void initUserMap(UserService userService) {
        List<LeUser> users = userService.findAllUsers();
        for(int index=0; index < users.size(); index++){
            LeUser user = users.get(index);
            Map<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("bean", user);
            usersMap.put(String.valueOf(user.getId()), userMap);
        }
    }
    static public LeUser getUserById(int id){
        return (LeUser) usersMap.get(String.valueOf(id)).get("bean");
    }
    static public void updateUserBean(LeUser user){
        usersMap.get(String.valueOf(user.getId())).put("bean", user);
    }
    static public LeUser getRandomUser(){
        Random random = new Random(); 
        Set userIds = usersMap.keySet();
        int selectedNumber = random.nextInt(userIds.size());
        int index=0;
        for (Iterator it = userIds.iterator(); it.hasNext();) {
            if(index != selectedNumber){
                continue;
            }else{
                String idString = (String) it.next();
                return UserCache.getUserById(Integer.valueOf(idString));
            }
        }
        return null;
    }
}
