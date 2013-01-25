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
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addComment")
    public @ResponseBody
    String addComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();

        //获取用户
        LeUser user = this.getRequestUser(request);
        if (user == null) {
            //用户未登录
            jsonObject.put(Const.STATUS, Const.FAILURE);
            jsonObject.put(Const.MESSAGE, "用户未登录");
            return jsonObject.toString();
        }

        //评论内容
        String content = this.getParaStringFromRequest("content");

        //所评论的对象Id
        int objectId = this.getParaIntFromRequest("objectId");
        if (objectId == -1) {
            //ObjectId错误
            jsonObject.put(Const.STATUS, Const.FAILURE);
            jsonObject.put(Const.MESSAGE, "未获取对象ID");
            return jsonObject.toString();
        }

        //所评论的对象类型，1表示图片
        int objectType = this.getParaIntFromRequest("objectType");
        if (objectType == -1) {
            //ObjectType错误
            jsonObject.put(Const.STATUS, Const.FAILURE);
            jsonObject.put(Const.MESSAGE, "未获取对象类型");
            return jsonObject.toString();

        }

        //发表该对象的用户Id
        int objectUserId = this.getParaIntFromRequest("objectUserId");
        if (objectType == -1) {
            //objectUserId
            jsonObject.put(Const.STATUS, Const.FAILURE);
            jsonObject.put(Const.MESSAGE, "未获取该对象拥有者ID");
            return jsonObject.toString();
        }

        LeComment comment = new LeComment();
        comment.setUserId(user.getId());
        comment.setUserName(user.getName());
        comment.setObjectType(objectType);//1表示评论的是图片
        comment.setObjectId(objectId);
        comment.setObjectUserId(objectUserId);
        comment.setContent(content);

        //添加评论
        commentService.addComment(comment);
        //如果是图片，更新图片评论数
        if (objectType == 1) {
            LePhoto photo = photoService.findPhotoById(objectId);
            photo.addCommentCount();
            photoService.updatePhoto(photo);
        }

        JSONArray jsonArray = new JSONArray();
        JSONObject tmpObject = new JSONObject()
                .element("id", comment.getId())
                .element("content", comment.getContent())
                .element("createTime", comment.getCreateTime())
                .element("userId", comment.getUserId())
                .element("userName", comment.getUserName())
                .element("userFace", UserCache.getUserById(comment.getUserId()).getFace())
                .element("channel", comment.getChannel());
        jsonArray.add(tmpObject);

        jsonObject.put("data", jsonArray);
        jsonObject.put("size", 1);
        return jsonObject.toString();

    }

    /**
     * 删除评论
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteComment")
    public @ResponseBody
    String deleteComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LeUser user = this.getRequestUser(request);
        int commentId = this.getParaIntFromRequest("commentId");

        if (user == null || commentId == -1) {
            return Const.FAILURE;
        }

        LeComment comment = commentService.findCommentById(commentId);
        int objectUserId = comment.getObjectUserId();
        int userId = comment.getUserId();
        //如果该用户既不是评论的创建者，也不是评论所评论对象的拥有者，则返回失败
        if (user.getId() != objectUserId && user.getId() != userId) {
            return Const.FAILURE;
        }
        commentService.delComment(comment);
        return Const.SUCCESS;
    }

    /**
     * 获取部分评论
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getLimitComments")
    public @ResponseBody
    String getLimitComments(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();

        //对象ID,如果ObjectType为1，表示photoId
        int objectId = this.getParaIntFromRequest("objectId");
        if (objectId == -1) {
            //ObjectId错误
            jsonObject.put(Const.STATUS, Const.FAILURE);
            jsonObject.put(Const.MESSAGE, "未获取对象ID");
            return jsonObject.toString();
        }

        //对象类型，1表示图片
        int objectType = this.getParaIntFromRequest("objectType");
        if (objectType == -1) {
            //ObjectType错误
            jsonObject.put(Const.STATUS, Const.FAILURE);
            jsonObject.put(Const.MESSAGE, "未获取对象类型");
            return jsonObject.toString();

        }

        List<LeComment> comments = commentService.getComments(objectType, objectId);
        int totalSize;
        if (comments == null) {
            totalSize = 0;
        } else {
            totalSize = comments.size();
        }
        JSONArray jsonArray = new JSONArray();
        LeComment comment;

        //如果评论数大于5，取前两条和后三条
        if (totalSize > 5) {
            for (int index = 0; index < 5; index++) {
                if (index < 2) {
                    comment = comments.get(index);
                } else {
                    comment = comments.get(totalSize - 5 + index);
                }
                JSONObject tmpObject = new JSONObject()
                        .element("id", comment.getId())
                        .element("content", comment.getContent())
                        .element("createTime", comment.getCreateTime())
                        .element("userId", comment.getUserId())
                        .element("userName", comment.getUserName())
                        .element("userFace", UserCache.getUserById(comment.getUserId()).getFace())
                        .element("channel", comment.getChannel());
                jsonArray.add(tmpObject);
            }
        } else {
            //如果评论数小于等于5条，则全取
            for (int index = 0; index < totalSize; index++) {
                comment = comments.get(index);
                JSONObject tmpObject = new JSONObject()
                        .element("id", comment.getId())
                        .element("content", comment.getContent())
                        .element("createTime", comment.getCreateTime())
                        .element("userId", comment.getUserId())
                        .element("userName", comment.getUserName())
                        .element("userFace", UserCache.getUserById(comment.getUserId()).getFace())
                        .element("channel", comment.getChannel());
                jsonArray.add(tmpObject);
            }
        }

        jsonObject.put("data", jsonArray);
        jsonObject.put("size", totalSize);
        return jsonObject.toString();
    }

    /**
     * 获取全部评论
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAllComments")
    public @ResponseBody
    String getAllComments(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();

        //对象ID,如果ObjectType为1，表示photoId
        int objectId = this.getParaIntFromRequest("objectId");
        if (objectId == -1) {
            //ObjectId错误
            jsonObject.put(Const.STATUS, Const.FAILURE);
            jsonObject.put(Const.MESSAGE, "未获取对象ID");
            return jsonObject.toString();
        }

        //对象类型，1表示图片
        int objectType = this.getParaIntFromRequest("objectType");
        if (objectType == -1) {
            //ObjectType错误
            jsonObject.put(Const.STATUS, Const.FAILURE);
            jsonObject.put(Const.MESSAGE, "未获取对象类型");
            return jsonObject.toString();
        }

        List<LeComment> comments = commentService.getComments(objectType, objectId);

        int totalSize;
        if (comments == null) {
            totalSize = 0;
        } else {
            totalSize = comments.size();
        }
        JSONArray jsonArray = new JSONArray();
        LeComment comment;
        for (int index = 0; index < totalSize; index++) {
            comment = comments.get(index);
            JSONObject tmpObject = new JSONObject()
                    .element("id", comment.getId())
                    .element("content", comment.getContent())
                    .element("createTime", comment.getCreateTime())
                    .element("userId", comment.getUserId())
                    .element("userName", comment.getUserName())
                    .element("userFace", UserCache.getUserById(comment.getUserId()).getFace())
                    .element("channel", comment.getChannel());
            jsonArray.add(tmpObject);
        }
        jsonObject.put("data", jsonArray);
        jsonObject.put("size", totalSize);
        return jsonObject.toString();
    }

    /**
     * 瀑布流类型获取评论方式，异步Ajax
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getMoreComments")
    public @ResponseBody
    String getMoreComments(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();

        //对象ID,如果ObjectType为1，表示photoId
        int objectId = this.getParaIntFromRequest("objectId");
        if (objectId == -1) {
            //ObjectId错误
            jsonObject.put(Const.STATUS, Const.FAILURE);
            jsonObject.put(Const.MESSAGE, "未获取对象ID");
            return jsonObject.toString();
        }

        //对象类型，1表示图片
        int objectType = this.getParaIntFromRequest("objectType");
        if (objectType == -1) {
            //ObjectType错误
            jsonObject.put(Const.STATUS, Const.FAILURE);
            jsonObject.put(Const.MESSAGE, "未获取对象类型");
            return jsonObject.toString();
        }

        //最后一条评论Id，类似于getPhoto中的lastPhotoId
        int lastCommentId = this.getParaIntFromRequest("lastCommentId");
        //一次要获取的评论数量，如果size为0，则表示获取全部剩余评论
        int size = this.getParaIntFromRequest("size");
        lastCommentId = lastCommentId == -1 ? 0 : lastCommentId;
        size = size == -1 ? 10 : size;

        List<LeComment> comments = commentService.getCommentsAjax(objectType, objectId, lastCommentId, size);

        int totalSize = comments == null ? 0 : comments.size();

        JSONArray jsonArray = new JSONArray();
        LeComment comment;
        for (int index = 0; index < totalSize; index++) {
            comment = comments.get(index);
            JSONObject tmpObject = new JSONObject()
                    .element("id", comment.getId())
                    .element("content", comment.getContent())
                    .element("createTime", comment.getCreateTime())
                    .element("userId", comment.getUserId())
                    .element("userName", comment.getUserName())
                    .element("userFace", UserCache.getUserById(comment.getUserId()).getFace())
                    .element("channel", comment.getChannel());
            jsonArray.add(tmpObject);
        }
        jsonObject.put("data", jsonArray);
        jsonObject.put("size", totalSize);
        return jsonObject.toString();
    }
}
