/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.cache;

import com.lefoto.model.user.LeRelationship;
import com.lefoto.model.user.LeUser;
import com.lefoto.model.user.LeUserStatus;
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
    static Map<String, Map<String, Object>> robootUsersMap = new HashMap<String, Map<String, Object>>();
    static Map<String, Integer> userNameMap = new HashMap<String, Integer>();

    /**
     * 初始化用户缓存 bean中存放LeUser类型的userBean followings中存放该用户所有关注人的userId
     * followers中存放该用户所有粉丝的userId followings中存放该用户所关注的用户的userId
     * status中存放该用户所有的状态信息，包括新回复，新转发，新@等
     *
     * @param userService
     */
    static public void initUserMap(UserService userService) {
        List<LeUser> users = userService.findAllUsers();
        List<LeRelationship> relationships = userService.findAllRelationships();
        List<LeUserStatus> userStatus = userService.findAllUserStatus();
        Map<String, LeUserStatus> userStatusMap = new HashMap<String, LeUserStatus>();
        if (userStatus != null) {
            for (LeUserStatus leUserStatus : userStatus) {
                userStatusMap.put(String.valueOf(leUserStatus.getUserId()), leUserStatus);
            }
        }
        for (int index = 0; index < users.size(); index++) {
            LeUser user = users.get(index);
            String userIdString = String.valueOf(user.getId());
            Map<String, List<Integer>> relationMap = getRelations(user, relationships);
            Map<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("bean", user);
            userMap.put("followings", relationMap.get("followings"));
            userMap.put("followers", relationMap.get("followers"));
            userMap.put("status", userStatusMap.get(userIdString));
            if (user.getType() == 1) {
                robootUsersMap.put(userIdString, userMap);
            }
            usersMap.put(userIdString, userMap);
        }
        System.out.println(usersMap.keySet());
        initUserNameMap(users);
    }

    static public void initUserNameMap(List<LeUser> users) {
        for (LeUser user : users) {
            userNameMap.put(user.getName(), user.getId());
        }
    }

    static public LeUser getUserById(int id) {
        Map userMap = usersMap.get(String.valueOf(id));
        if (userMap != null) {
            return (LeUser) userMap.get("bean");
        } else {
            return null;
        }
    }

    static public LeUser getUserByName(String userName) {
        int userId = userNameMap.get(userName);
        Map userMap = usersMap.get(String.valueOf(userId));
        if (userMap != null) {
            return (LeUser) userMap.get("bean");
        } else {
            return null;
        }
    }

    static public int getUserIdByUserName(String userName) {
        return userNameMap.get(userName);
    }

    static public LeUserStatus getUserStatusByUserId(int userId) {
        Map userMap = usersMap.get(String.valueOf(userId));
        if (userMap != null) {
            return (LeUserStatus) userMap.get("status");
        } else {
            return null;
        }
    }

    static public String getUserNameById(int id) {
        Map userMap = usersMap.get(String.valueOf(id));
        if (userMap != null) {
            return ((LeUser) userMap.get("bean")).getName();
        } else {
            return null;
        }
    }

    static public List<Integer> getFollowingsByUserId(int userId) {
        Map userMap = usersMap.get(String.valueOf(userId));
        if (userMap != null) {
            return (List<Integer>) userMap.get("followings");
        } else {
            return null;
        }
    }

    static public List<Integer> getFollowersByUserId(int userId) {
        Map userMap = usersMap.get(String.valueOf(userId));
        if (userMap != null) {
            return (List<Integer>) userMap.get("followers");
        } else {
            return null;
        }
    }

    static public void updateUserBean(LeUser user) {
        Map userMap = usersMap.get(String.valueOf(user.getId()));
        if (userMap != null) {
            userMap.put("bean", user);
        }
    }

    static public void updateUserStatus(LeUserStatus userStatus) {
        Map userMap = usersMap.get(String.valueOf(userStatus.getUserId()));
        if (userMap != null) {
            userMap.put("status", userStatus);
        }
    }

    static public LeUser getRandomRobootUser() {
        Random random = new Random();
        Set userIds = robootUsersMap.keySet();
        if (userIds == null || userIds.isEmpty()) {
            if (userIds == null) {
                System.out.println("异常-虚拟用户为NULL!");
                return null;
            }
            if (userIds.isEmpty()) {
                System.out.println("异常-虚拟用户数量为0!");
                return null;
            }
        }
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

    public static void delUser(LeUser user) {
        usersMap.remove(user);

    }

    public static void addUser(LeUser user) {
        String userIdString = String.valueOf(user.getId());
        if (usersMap.get(userIdString) == null) {
            Map<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("bean", user);
            //TODO followings, followers, userStatus木有添加
            usersMap.put(userIdString, userMap);
        }
    }
}
