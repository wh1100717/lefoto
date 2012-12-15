package com.lefoto.controller.backManage;

import com.lefoto.common.base.BaseController;
import com.lefoto.model.media.LeAlbum;
import com.lefoto.model.media.LeCategory;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.media.AlbumService;
import com.lefoto.service.iface.media.CategoryService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台管理Controller
 * 主要负责显示各个模块
 * @author Eric
 */
@Controller
@RequestMapping("/back")
public class BackIndexController extends BaseController {

    /**
     * 显示后台管理的主页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/back/index");
        return mv;
    }
    @Autowired
    AlbumService albumService;

    /**
     * 显示相册管理模块
     * @param request
     * @return
     */
    @RequestMapping(value = "/albumManage")
    public ModelAndView albumManage(HttpServletRequest request) {
        LeUser user = this.getRequestUser(request);
        ModelAndView mv = new ModelAndView("/back/albumManage");
        List<LeAlbum> albums = albumService.findAlbumsByUserId(user.getId());
        mv.addObject("albums", albums);

        return mv;
    }

    /**
     * 显示照片管理模块
     * @param request
     * @return
     */
    @RequestMapping(value = "/photoManage")
    public ModelAndView photoManage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/back/photoManage");
        return mv;
    }
    @Autowired
    CategoryService categoryService;

    /**
     * 显示分类管理模块
     * @param request
     * @return
     */
    @RequestMapping(value = "/categoryManage")
    public ModelAndView categoryManage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/back/categoryManage");
        List<LeCategory> categories = categoryService.findCategories();
        mv.addObject("categories", categories);
        return mv;
    }
    /**
     * 显示默认用户头像管理模块
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/defaultUserFace")
    public ModelAndView defaultUserFaceManage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/back/user/defaultUserFace");
        return mv;
    }

}