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

    /**
     * 添加评论
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addComment")
    public @ResponseBody
    String addComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取请求参数
        //所要回复的评论的Id
        int replyCommentId = this.getParaIntFromRequest("replyCommentId");
        //所要回复的评论的用户Id
        int replyUserId = this.getParaIntFromRequest("replyUserId");
        //所要回复的评论的用户名，直接从缓存获取
        String replyUserName = UserCache.getUserById(replyUserId).getName();
        //所评论的对象Id
        int objectId = this.getParaIntFromRequest("objectId");
        //所评论的对象类型，1表示图片
        int objectType = this.getParaIntFromRequest("objectType");
        //发表该对象的用户Id
        int objectUserId = this.getParaIntFromRequest("objectUserId");
        //发表该对象的用户名，直接从缓存获取
        String objectUserName = UserCache.getUserById(objectUserId).getName();

        LeUser user = this.getRequestUser(request);
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

    /**
     * 删除评论
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteComment")
    public @ResponseBody
    String deleteComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LeUser user = this.getRequestUser(request);
        if(user == null){return Const.FAILURE;}
        int commentId = this.getParaIntFromRequest("commentId");
        LeComment comment = commentService.findCommentById(commentId);
        int objectUserId = comment.getObjectUserId();
        int userId = comment.getUserId();
        //如果该用户既不是评论的创建者，也不是评论所评论对象的拥有者，则返回失败
        if(user.getId() != objectUserId && user.getId() != userId){
            return Const.FAILURE;
        }
        commentService.delComment(comment);
        return Const.SUCCESS;
    }

    /**
     * 获取部分评论
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getLimitComments")
    public @ResponseBody
    List<String> getLimitComments(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List result = new ArrayList();

        //对象ID,如果ObjectType为1，表示photoId
        int objectId = this.getParaIntFromRequest("objectId");
        //对象类型，1表示图片
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

    /**
     * 获取全部评论
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAllComments")
    public @ResponseBody
    List<String> getAllComments(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List result = new ArrayList();

        //对象ID,如果ObjectType为1，表示photoId
        int objectId = this.getParaIntFromRequest("objectId");
        //对象类型，1表示图片
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

    /**
     * 瀑布流类型获取评论方式，异步Ajax
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getMoreComments")
    public @ResponseBody
    List<String> getMoreComments(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List result = new ArrayList();

        //对象ID,如果ObjectType为1，表示photoId
        int objectId = this.getParaIntFromRequest("objectId");
        //对象类型，1表示图片
        int objectType = this.getParaIntFromRequest("objectType");
        //最后一条评论Id，类似于getPhoto中的lastPhotoId
        int lastCommentId = this.getParaIntFromRequest("lastCommentId");
        //一次要获取的评论数量，如果size为0，则表示获取全部剩余评论
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
