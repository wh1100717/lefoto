/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.impl.content;

import com.lefoto.common.cache.UserCache;
import com.lefoto.dao.iface.content.CommentDao;
import com.lefoto.dao.iface.user.UserDao;
import com.lefoto.model.content.LeComment;
import com.lefoto.model.user.LeAtUser;
import com.lefoto.model.user.LeUserStatus;
import com.lefoto.service.iface.content.CommentService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图片Service的实现类
 *
 * @author Eric
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;

    @Override
    public void addComment(LeComment comment) {
        Map<String, String> result = parseCommentContent(comment);
        comment.setContent(result.get("content"));

        commentDao.addComment(comment);

        //对被At的用户的处理
        String atUserNameString = result.get("atUserNames");
        if (!atUserNameString.equals("")) {
            String[] atUserNames = atUserNameString.split(",");
            for (String atUserName : atUserNames) {
                int atUserId = UserCache.getUserIdByUserName(atUserName);
                LeAtUser atUser = new LeAtUser();
                //objectType为0，表示是评论中at了用户
                atUser.setObjectType(0);
                atUser.setObjectId(comment.getId());
                atUser.setUserId(atUserId);
                userDao.addAtUser(atUser);
                LeUserStatus userStatus = userDao.findUserStatus(atUserId);
                if (userStatus != null) {
                    userStatus.setNewAtCount(userStatus.getNewAtCount() + 1);
                    userDao.updateUserStatus(userStatus);
                }

            }
        }

    }

    @Override
    public void delComment(LeComment comment) {
        commentDao.delComment(comment);
    }

    @Override
    public LeComment findCommentById(int id) {
        return commentDao.findCommentById(id);
    }

    @Override
    public List<LeComment> getComments(int objectType, int objectId) {
        return commentDao.getComments(objectType, objectId);
    }

    @Override
    public List<LeComment> getCommentsAjax(int objectType, int objectId, int lastCommentId, int size) {
        return commentDao.getCommentsAjax(objectType, objectId, lastCommentId, size);
    }

    private Map<String, String> parseCommentContent(LeComment comment) {
        Map userMap = new HashMap();
        Map<String, String> result = new HashMap<String, String>();
        String content = comment.getContent();
        String newContent = content;
        String atUserNames = "";
        int objectType = comment.getObjectType();
        int objectId = comment.getObjectId();

        //将对该图片评论的用户添加到userMap中
        List<LeComment> comments = commentDao.getComments(objectType, objectId);
        if (comments != null) {
            for (LeComment leComment : comments) {
                userMap.put(leComment.getUserName(), 1);
            }
        }

        //将该用户所关注的用户添加到userMap中
        List<Integer> followingIds = UserCache.getFollowersByUserId(comment.getUserId());
        if (followingIds != null) {
            for (Integer followingId : followingIds) {
                String userName = UserCache.getUserNameById(followingId);
                if (userName != null) {
                    userMap.put(userName, 1);
                }
            }
        }
        String regex = "(@[^ ]*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(content);
        while (match.find()) {
            String userName = match.group();
            System.out.println(userName);
            if (userMap.get(userName) != null) {
                newContent = newContent.replace("@" + userName, "[at]@" + userName + "[/at]");
                if (atUserNames.equals("")) {
                    atUserNames = userName;
                } else {
                    atUserNames = atUserNames + "," + userName;
                }
            }
        }
        result.put("content", newContent);
        result.put("atUserNames", atUserNames);
        return result;
    }
}
