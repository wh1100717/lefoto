/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.user;

import com.lefoto.common.base.BaseController;
import com.lefoto.common.base.Const;
import com.lefoto.common.cache.UserCache;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.content.CommentService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Eric
 */
@Controller
@RequestMapping("/user")
public class userController extends BaseController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/searchAtUsers")
    public @ResponseBody
    List<String> searchAtUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List result = new ArrayList();
        Map<String, LeUser> userMap = new HashMap<String, LeUser>();
        String content = this.getParaStringFromRequest("content");
        String commentUserIds = this.getParaStringFromRequest("commentUserIds");
        LeUser ownUser = this.getRequestUser(request);
        LeUser user;
        if (commentUserIds != null && !commentUserIds.equals("")) {
            String[] userIds = commentUserIds.split(",");
            for (String userId : userIds) {
                user = UserCache.getUserById(Integer.parseInt(userId));
                if (user == null) {
                    continue;
                }
                userMap.put(user.getName(), user);
            }
        }
        List<Integer> followingIds = UserCache.getFollowingsByUserId(ownUser.getId());
        for (int followingId : followingIds) {
            user = UserCache.getUserById(followingId);
            if (user == null) {
                continue;
            }
            userMap.put(user.getName(), user);
        }
        Set userNames = userMap.keySet();
        JSONArray jsonArray = new JSONArray();
        for (Iterator it = userNames.iterator(); it.hasNext();) {
            String userName = (String) it.next();
            if (userName.contains(content)) {
                user = userMap.get(userName);
                if (user == null) {
                    continue;
                }
                JSONObject tmpObject = new JSONObject()
                        .element("userId", user.getId())
                        .element("userName", user.getName())
                        .element("face", user.getFace());
                jsonArray.add(tmpObject);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        jsonObject.put("msg", Const.SUCCESS);
        result.add(jsonObject.toString());
        return result;
    }
}
