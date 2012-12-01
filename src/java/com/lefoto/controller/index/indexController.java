/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.index;

import com.lefoto.common.base.BaseController;
import com.lefoto.model.media.LePhoto;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.media.PhotoService;
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
@RequestMapping(value = "/index")
public class indexController extends BaseController {

    /**
     * 渲染网站首页，根据是否登录显示不同的内容
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/show")
    public ModelAndView show(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/index/home");
        LeUser user = this.getUser();
        if (user != null) {
            mv.addObject("user", user);
        }
        return mv;
    }

    @Autowired
    PhotoService photoService;
    
    @RequestMapping(value = "/getPhoto")
    public @ResponseBody
    String getPic(HttpServletRequest request) {
        int cateId = this.getParaIntFromRequest("cateId");
        int lastPhotoId = this.getParaIntFromRequest("lastPhotoId");
        int size = this.getParaIntFromRequest("size");
        List photos = photoService.getPhotos(cateId, lastPhotoId, size);
        JSONArray jsonArray = new JSONArray();
        for(int index=0; index<photos.size(); index++){
            LePhoto photo = (LePhoto) photos.get(index);
            JSONObject tmpObject = new JSONObject()
                    .element("id", photo.getId())
                    .element("url", photo.getUrl()+"!300")
                    .element("description", photo.getDescription())
                    .element("downCount", photo.getDownCount())
                    .element("upCount", photo.getUpCount())
                    .element("forwardCount", photo.getForwardCount())
                    .element("commentCount", photo.getCommentCount())
                    .element("userId", photo.getUserId())
                    .element("userName", photo.getUserName())
                    .element("height", photo.getHeight()*300/photo.getWidth());
            jsonArray.add(tmpObject);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        jsonObject.put("中文", ((LePhoto)photos.get(0)).getUserName());
        return jsonObject.toString();
    }
}
