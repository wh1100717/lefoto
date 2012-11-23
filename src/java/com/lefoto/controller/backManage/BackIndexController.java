package com.lefoto.controller.backManage;

import com.lefoto.common.base.BaseController;
import com.lefoto.model.media.LeAlbum;
import com.lefoto.model.media.LeCategory;
import com.lefoto.service.iface.media.AlbumService;
import com.lefoto.service.iface.media.CategoryService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Eric
 */
@Controller
@RequestMapping("/back")
public class BackIndexController extends BaseController {

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/back/index");
        return mv;
    }
    @Autowired
    AlbumService albumService;

    @RequestMapping(value = "/albumManage")
    public ModelAndView albumManage(HttpServletRequest request) {
        this.execute(request);
        ModelAndView mv = new ModelAndView("/back/albumManage");
        List<LeAlbum> albums = albumService.findAlbumsByUserId(this.getUser().getId());
        mv.addObject("albums", albums);

        return mv;
    }

    @RequestMapping(value = "/photoManage")
    public ModelAndView photoManage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/back/photoManage");
        return mv;
    }
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/categoryManage")
    public ModelAndView categoryManage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/back/categoryManage");
        List<LeCategory> categories = categoryService.findCategories();
        mv.addObject("categories", categories);

        return mv;
    }
}