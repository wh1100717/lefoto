/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.index;

import com.lefoto.common.base.BaseController;
import com.lefoto.user.model.LeUser;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Eric
 */
@Controller
@RequestMapping(value = "/index")
public class indexController extends BaseController {

    LeUser user;

    @RequestMapping(value = "/show")
    public ModelAndView show(HttpServletRequest request) {
        ModelAndView mv;
        user = this.getRequestUser(request);
        if (user == null) {
            //未登录状态的主页
            mv = new ModelAndView("/index/index");
        } else {
            mv = new ModelAndView("/index/home");
            //登录状态下的主页
        }
        return mv;
    }
}
