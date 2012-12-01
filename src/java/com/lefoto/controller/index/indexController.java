/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.index;

import com.lefoto.common.base.BaseController;
import com.lefoto.model.user.LeUser;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 网站首页
 * @author Eric
 */
@Controller
@RequestMapping(value = "/index")
public class indexController extends BaseController {

    /**
     * 渲染网站首页，根据是否登录显示不同的内容
     * @param request
     * @return
     */
    @RequestMapping(value = "/show")
    public ModelAndView show(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/index/home");
        LeUser user = this.getUser();

        if(user != null){
            mv.addObject("user", user);
            
        }
        return mv;
    }
}
