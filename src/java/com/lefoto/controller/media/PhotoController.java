/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.media;

import com.lefoto.common.base.BaseController;
import com.lefoto.common.base.Const;
import com.lefoto.common.cache.PhotoCache;
import com.lefoto.common.cache.UserCache;
import com.lefoto.common.utils.AuthenUtil;
import com.lefoto.common.utils.FormatUtil;
import com.lefoto.common.utils.UpYunUtil;
import com.lefoto.model.content.LeComment;
import com.lefoto.model.media.LePhoto;
import com.lefoto.model.media.LePhotoUp;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.content.CommentService;
import com.lefoto.service.iface.media.PhotoService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Eric
 */
@Controller
@RequestMapping("/photo")
public class PhotoController extends BaseController {

    @Autowired
    PhotoService photoService;
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/detail")
    public ModelAndView show(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/photo/detail");
        int photoId = this.getParaIntFromRequest("photoId");
        if (photoId == -1) {
            return new ModelAndView(new RedirectView("/index.html"));
        }
        LePhoto photo = photoService.findPhotoById(photoId);
        List<LeComment> comments = commentService.getCommentsAjax(0, photo.getId(), 0, 20);
        mv.addObject("photo", photo);
        mv.addObject("comments", comments);
        return mv;
    }

    @RequestMapping(value = "/getPhotos")
    public @ResponseBody
    String getPhoto(HttpServletRequest request) throws IOException {
        LeUser ownUser = this.getRequestUser(request);
        //验证是否是本网站发出的请求
        if (!AuthenUtil.refererAuthen(request.getHeader("Referer"))) {
            return "invalid request";
        }
        //验证是否是浏览器发出的请求
        if (!AuthenUtil.userAgentAuthen(request.getHeader("User-Agent"))) {
            return "invalid request";
        }

        int cateId = this.getParaIntFromRequest("cateId");
        int lastPhotoId = this.getParaIntFromRequest("lastPhotoId");
        int size = this.getParaIntFromRequest("size");
        //type : 0表示按时间顺序排序 | 1表示按热度排序 | 2便是随便看看  也就是随机排序
        int type = this.getParaIntFromRequest("type");
//        List photos = photoService.getPhotos(cateId, lastPhotoId, size, type);

        cateId = cateId == -1 ? Const.DEFAULT_CATEGORY_ID : cateId;
        lastPhotoId = lastPhotoId == -1 ? 0 : lastPhotoId;
        size = size == -1 ? 10 : size;
        type = type == -1 ? Const.DEFAULT_BROWSE_TYPE : type;

        List photos = PhotoCache.getPhotos(cateId, lastPhotoId, size, type);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        if (photos == null) {
            jsonObject.put("data", jsonArray);
            return jsonObject.toString();
        }
        LePhotoUp up = null;
        for (int index = 0; index < photos.size(); index++) {
            LePhoto photo = (LePhoto) photos.get(index);
            LeUser user = UserCache.getUserById(photo.getUserId());
            if (ownUser != null) {
                up = PhotoCache.findPhotoUp(photo.getId(), ownUser.getId());
            }
            int height = photo.getHeight();
            int width = photo.getWidth();
            if (width > 420) {
                height = height * 420 / width;
            }
            JSONObject tmpObject = new JSONObject()
                    .element("id", photo.getId())
                    .element("url", photo.getUrl() + "!420")
                    .element("description", photo.getDescription())
                    .element("downCount", photo.getDownCount() == 0 ? "" : photo.getDownCount())
                    .element("upCount", photo.getUpCount() == 0 ? "" : photo.getUpCount())
                    .element("forwardCount", photo.getForwardCount() == 0 ? "" : photo.getForwardCount())
                    .element("commentCount", photo.getCommentCount() == 0 ? "" : photo.getCommentCount())
                    .element("userId", photo.getUserId())
                    .element("userName", photo.getUserName())
                    .element("face", user.getFace() + "!small")
                    .element("height", height)
                    .element("url_tag", FormatUtil.getUrlTag(photo.getUrl()));
            if (up != null) {
                tmpObject.put("up", 1);
            } else {
                tmpObject.put("up", 0);
            }
            jsonArray.add(tmpObject);
        }
        jsonObject.put("data", jsonArray);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/deletePhotoByAdmin")
    public @ResponseBody
    String deletePhotoByAdmin(HttpServletRequest request) {
        LeUser user = this.getRequestUser(request);
        if (user == null || !user.getEmail().equals("admin@lefoto.me")) {
            return Const.FAILURE;
        }
        int photoId = this.getParaIntFromRequest("photoId");
        int cateId = this.getParaIntFromRequest("cateId");
        if (photoId == -1 || cateId == -1) {
            return Const.FAILURE;
        }
        try {
            //从数据库和缓存中删除图片
            LePhoto photo = PhotoCache.getPhotoById(photoId, cateId);
            photoService.deletePhoto(photo);
            //从又拍云上删除图片
            UpYunUtil.delete(photo.getUrl());
        } catch (Exception e) {
            return Const.FAILURE;
        }
        return Const.SUCCESS;
    }

    @RequestMapping(value = "/deletePhoto")
    public @ResponseBody
    String deletePhoto(HttpServletRequest request) {
        LeUser user = this.getRequestUser(request);
        int photoId = this.getParaIntFromRequest("photoId");
        if (user == null || photoId == -1) {
            return Const.FAILURE;
        }
        LePhoto photo = photoService.findPhotoById(photoId);
        if (photo == null) {
            return Const.FAILURE;
        }
        if (photo.getUserId() != user.getId()) {
            return Const.FAILURE;
        }
        photoService.deletePhoto(photo);
        return Const.SUCCESS;
    }

    @RequestMapping(value = "/upPhoto")
    public @ResponseBody
    String upPhoto(HttpServletRequest request) {
        LeUser user = this.getRequestUser(request);
        int photoId = this.getParaIntFromRequest("photoId");
        if (user == null || photoId == -1) {
            return Const.FAILURE;
        }
        photoService.upPhoto(photoId, user.getId());
        return Const.SUCCESS;
    }

    @RequestMapping(value = "/cancelUpPhoto")
    public @ResponseBody
    String cancelUpPhoto(HttpServletRequest request) {
        LeUser user = this.getRequestUser(request);
        int photoId = this.getParaIntFromRequest("photoId");
        if (user == null || photoId == -1) {
            return Const.FAILURE;
        }
        photoService.cancelUpPhoto(photoId, user.getId());
        return Const.SUCCESS;
    }

    @RequestMapping(value = "/getUpUsers")
    public ModelAndView getUpUsers(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/index/likeUsers");
        //List result = new ArrayList();
        int photoId = this.getParaIntFromRequest("photoId");
        if (photoId == -1) {
            return mv;
        }
        List<LePhotoUp> ups = PhotoCache.findPhotoUps(photoId);

        List<LeUser> users = new ArrayList<LeUser>();
        if (ups == null) {
            return mv;
        }
        for (LePhotoUp up : ups) {
            LeUser user = UserCache.getUserById(up.getUserId());
            if (user == null) {
                continue;
            }
            users.add(user);
        }
        mv.addObject("upUsers", users);
        return mv;
    }
}
