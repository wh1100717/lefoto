/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.cache;

import com.lefoto.model.user.LeRelationship;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.user.UserService;
import java.util.ArrayList;
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

    static Map<String, Map<String, Object>> usersMap = new HashMap<String, Map<String, Object>>();

    /**
     * 初始化用户缓存 bean中存放LeUser类型的userBean followings中存放该用户所有关注人的userId
     * followers中存放该用户所有粉丝的userId
     *
     * @param userService
     */
    static public void initUserMap(UserService userService) {
        List<LeUser> users = userService.findAllUsers();
        List<LeRelationship> relationships = userService.findAllRelationships();
        for (int index = 0; index < users.size(); index++) {
            LeUser user = users.get(index);
            Map<String, List<Integer>> relationMap = getRelations(user, relationships);
            Map<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("bean", user);
            userMap.put("followings", relationMap.get("followings"));
            userMap.put("followers", relationMap.get("followers"));
            usersMap.put(String.valueOf(user.getId()), userMap);
        }
    }

    static public LeUser getUserById(int id) {
        Map user = usersMap.get(String.valueOf(id));
        if (user != null) {
            return (LeUser) user.get("bean");
        } else {
            return null;
        }
    }

    static public String getUserNameById(int id) {
        Map user = usersMap.get(String.valueOf(id));
        if (user != null) {
            return ((LeUser) user.get("bean")).getName();
        } else {
            return null;
        }
    }

    static public List<Integer> getFollowingsByUserId(int userId) {
        Map user = usersMap.get(String.valueOf(userId));
        if (user != null) {
            return (List<Integer>) user.get("followings");
        } else {
            return null;
        }
    }

    static public List<Integer> getFollowersByUserId(int userId) {
        Map user = usersMap.get(String.valueOf(userId));
        if (user != null) {
            return (List<Integer>) user.get("followers");
        } else {
            return null;
        }
    }

    static public void updateUserBean(LeUser user) {
        usersMap.get(String.valueOf(user.getId())).put("bean", user);
    }

    static public LeUser getRandomUser() {
        Random random = new Random();
        Set userIds = usersMap.keySet();
        int selectedNumber = random.nextInt(userIds.size());
        int index = 0;
        for (Iterator it = userIds.iterator(); it.hasNext();) {
            if (index != selectedNumber) {
                index++;
                it.next();
                continue;
            } else {
                String idString = (String) it.next();
                return UserCache.getUserById(Integer.valueOf(idString));
            }
        }
        return null;
    }

    static private Map<String, List<Integer>> getRelations(LeUser user, List<LeRelationship> relationships) {
        Map<String, List<Integer>> result = new HashMap<String, List<Integer>>();
        List<Integer> followings = new ArrayList();
        List<Integer> followers = new ArrayList();
        if (user == null) {
            return result;
        }
        if (relationships == null) {
            return result;
        }
        int userId = user.getId();
        for (LeRelationship relation : relationships) {
            if (userId == relation.getUserId()) {
                followings.add(relation.getFollowingUserId());
                if (relation.getRelationType() == 2) {
                    followers.add(relation.getFollowingUserId());
                }
            }
            if (userId == relation.getFollowingUserId()) {
                followers.add(relation.getUserId());
                if (relation.getRelationType() == 2) {
                    followings.add(relation.getUserId());
                }
            }
        }
        result.put("followings", followings);
        result.put("followers", followers);
        return result;
    }
}
