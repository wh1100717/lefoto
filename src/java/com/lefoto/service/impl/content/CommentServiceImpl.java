/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.impl.content;

import com.lefoto.common.cache.UserCache;
import com.lefoto.dao.iface.content.CommentDao;
import com.lefoto.model.content.LeComment;
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

    @Override
    public void addComment(LeComment comment) {
        Map<String, String> result = parseCommentContent(comment);
        comment.setContent(result.get("content"));
        //TODO 需要去做对被At的用户的处理

        commentDao.addComment(comment);
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
        List<LeComment> comments = commentDao.getComments(objectType, objectId);
        for (LeComment leComment : comments) {
            userMap.put(leComment.getUserName(), 1);
        }
        //TODO 需要把该用户的好友添加到userMap中
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
