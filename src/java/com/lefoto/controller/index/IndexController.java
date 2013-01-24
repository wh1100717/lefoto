/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.index;

import com.lefoto.common.base.BaseController;
import com.lefoto.common.cache.PhotoCache;
import com.lefoto.common.cache.UserCache;
import com.lefoto.common.utils.AuthenUtil;
import com.lefoto.model.media.LePhoto;
import com.lefoto.model.media.LePhotoUp;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.media.PhotoService;
import com.lefoto.service.iface.user.UserService;
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

/**
 * 网站首页
 *
 * @author Eric
 */
@Controller
public class IndexController extends BaseController {

    /**
     * 渲染网站首页，根据是否登录显示不同的内容
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView show(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/index/home");
        LeUser homeUser = this.getRequestUser(request);
        int cateId = this.getParaIntFromRequest("cateId");
        int type = this.getParaIntFromRequestCheckZero("type");
        cateId = cateId == 0 ? 2 : cateId;
        type = type == -1 ? 2 : type;
        if (homeUser != null && homeUser.getEmail().equals("admin@lefoto.me")) {
            mv.addObject("delete", 1);
        }
        mv.addObject("cateId", cateId);
        mv.addObject("type", type);
        return mv;
    }
    @Autowired
    PhotoService photoService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/index/getPhoto")
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
                    .element("downCount", photo.getDownCount() == 0 ? "":photo.getDownCount())
                    .element("upCount", photo.getUpCount() == 0 ? "" : photo.getUpCount())
                    .element("forwardCount", photo.getForwardCount() == 0 ? "" : photo.getForwardCount())
                    .element("commentCount", photo.getCommentCount() == 0 ? "" : photo.getCommentCount())
                    .element("userId", photo.getUserId())
                    .element("userName", photo.getUserName())
                    .element("face", user.getFace() + "!small")
                    .element("height", height);
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
}
