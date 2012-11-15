/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.index;

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

    @RequestMapping(value = "/show")
    public ModelAndView show(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/index/home");
        return mv;
    }
}
