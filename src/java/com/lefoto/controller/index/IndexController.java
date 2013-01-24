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
}
