package com.lefoto.controller.backManage;

import com.lefoto.common.base.BaseController;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Eric
 */
@Controller
@RequestMapping("/back")
public class backIndexController extends BaseController {

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/back/index");
        return mv;
    }
    @RequestMapping(value = "/albumManage")
    public ModelAndView albumManage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/back/albumManage");
        return mv;
    }
    @RequestMapping(value = "/photoManage")
    public ModelAndView photoManage(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/back/photoManage");
        return mv;
    }

}