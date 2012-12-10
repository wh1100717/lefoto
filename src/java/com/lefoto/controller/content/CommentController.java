/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.content;

import com.lefoto.common.base.BaseController;
import com.lefoto.common.base.Const;
import com.lefoto.common.cache.UserCache;
import com.lefoto.model.content.LeComment;
import com.lefoto.model.media.LePhoto;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.content.CommentService;
import com.lefoto.service.iface.media.PhotoService;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/comment")
public class CommentController extends BaseController {

    @Autowired
    CommentService commentService;
    @Autowired
    PhotoService photoService;

    @RequestMapping(value = "/addComment")
    public @ResponseBody
    String addComment(HttpServletRequest requese, HttpServletResponse response) throws Exception {
        //初始化
        this.execute(requese);

        //获取请求参数
        int replyCommentId = this.getParaIntFromRequest("replyCommentId");
        int replyUserId = this.getParaIntFromRequest("replyUserId");
        String replyUserName = this.getParaStringFromRequest("replyUserName");
        int objectId = this.getParaIntFromRequest("objectId");
        int objectType = this.getParaIntFromRequest("objectType");
        int objectUserId = this.getParaIntFromRequest("objectUserId");
        String objectUserName = this.getParaStringFromRequest("objectUserName");

        LeUser user = this.getUser();
        LeComment comment = new LeComment();
        comment.setUserId(user.getId());
        comment.setUserName(user.getName());
        comment.setReplyCommentId(replyCommentId);
        comment.setReplyUserId(replyUserId);
        comment.setReplyUserName(replyUserName);
        comment.setObjectType(objectType);//0表示评论的是图片
        comment.setObjectId(objectId);
        comment.setObjectUserId(objectUserId);
        comment.setObjectUserName(objectUserName);

        //添加评论
        commentService.addComment(comment);
        //如果是图片，更新图片评论数
        if (objectType == 0) {
            LePhoto photo = photoService.findPhotoById(objectId);
            photo.addCommentCount();
            photoService.updatePhoto(photo);
        }

        return Const.SUCCESS;
    }

    @RequestMapping(value = "/getLimitComments")
    public @ResponseBody
    List<String> getLimitComments(HttpServletRequest requese, HttpServletResponse response) throws Exception {
        List result = new ArrayList();

        int objectId = this.getParaIntFromRequest("objectId");
        int objectType = this.getParaIntFromRequest("objectType");

        List<LeComment> comments = commentService.getComments(objectType, objectId);

        int size = comments.size();
        JSONArray jsonArray = new JSONArray();
        LeComment comment;

        //如果评论数大于5，取前两条和后三条
        if (size > 5) {
            for (int index = 0; index < 5; index++) {
                if (index < 2) {
                    comment = comments.get(index);
                } else {
                    comment = comments.get(size - 5 + index);
                }
                JSONObject tmpObject = new JSONObject()
                        .element("id", comment.getId())
                        .element("content", comment.getContent())
                        .element("createTime", comment.getCreateTime())
                        .element("userId", comment.getUserId())
                        .element("userName", comment.getUserName())
                        .element("userFace", UserCache.getUserById(comment.getUserId()).getFace())
                        .element("replyUserId", comment.getReplyUserId())
                        .element("replyUserName", comment.getReplyUserName())
                        .element("channel", comment.getChannel());
                jsonArray.add(tmpObject);
            }
        } else {
            //如果评论数小于等于5条，则全取
            for (int index = 0; index < size; index++) {
                comment = comments.get(index);
                JSONObject tmpObject = new JSONObject()
                        .element("id", comment.getId())
                        .element("content", comment.getContent())
                        .element("createTime", comment.getCreateTime())
                        .element("userId", comment.getUserId())
                        .element("userName", comment.getUserName())
                        .element("userFace", UserCache.getUserById(comment.getUserId()).getFace())
                        .element("replyUserId", comment.getReplyUserId())
                        .element("replyUserName", comment.getReplyUserName())
                        .element("channel", comment.getChannel());
                jsonArray.add(tmpObject);
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        jsonObject.put("size", size);

        return result;
    }

    @RequestMapping(value = "/getComments")
    public @ResponseBody
    List<String> getComments(HttpServletRequest requese, HttpServletResponse response) throws Exception {
        List result = new ArrayList();

        int objectId = this.getParaIntFromRequest("objectId");
        int objectType = this.getParaIntFromRequest("objectType");

        List<LeComment> comments = commentService.getComments(objectType, objectId);

        int size = comments.size();
        JSONArray jsonArray = new JSONArray();
        LeComment comment;
        for (int index = 0; index < size; index++) {
            comment = comments.get(index);
            JSONObject tmpObject = new JSONObject()
                    .element("id", comment.getId())
                    .element("content", comment.getContent())
                    .element("createTime", comment.getCreateTime())
                    .element("userId", comment.getUserId())
                    .element("userName", comment.getUserName())
                    .element("userFace", UserCache.getUserById(comment.getUserId()).getFace())
                    .element("replyUserId", comment.getReplyUserId())
                    .element("replyUserName", comment.getReplyUserName())
                    .element("channel", comment.getChannel());
            jsonArray.add(tmpObject);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        jsonObject.put("size", size);

        return result;
    }

    @RequestMapping(value = "/getMoreComments")
    public @ResponseBody
    List<String> getMoreComments(HttpServletRequest requese, HttpServletResponse response) throws Exception {
         List result = new ArrayList();

        int objectId = this.getParaIntFromRequest("objectId");
        int objectType = this.getParaIntFromRequest("objectType");
        int lastCommentId = this.getParaIntFromRequest("lastCommentId");
        int size = this.getParaIntFromRequest("size");
        
        List<LeComment> comments = commentService.getCommentsAjax(objectType, objectId, lastCommentId, size);

        JSONArray jsonArray = new JSONArray();
        LeComment comment;
        for (int index = 0; index < comments.size(); index++) {
            comment = comments.get(index);
            JSONObject tmpObject = new JSONObject()
                    .element("id", comment.getId())
                    .element("content", comment.getContent())
                    .element("createTime", comment.getCreateTime())
                    .element("userId", comment.getUserId())
                    .element("userName", comment.getUserName())
                    .element("userFace", UserCache.getUserById(comment.getUserId()).getFace())
                    .element("replyUserId", comment.getReplyUserId())
                    .element("replyUserName", comment.getReplyUserName())
                    .element("channel", comment.getChannel());
            jsonArray.add(tmpObject);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        jsonObject.put("size", comments.size());

        return result;
    }
}
