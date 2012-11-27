/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.index;

import com.lefoto.common.base.Const;
import com.lefoto.common.utils.CipherUtil;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.user.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 登录模块
 * @author Eric
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 渲染登录页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/show")
    public ModelAndView show(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/index/login/show");
        return mv;
    }

    /**
     * 提交登录信息，对登录信息进行审核
     * @param request
     * @return
     */
    @RequestMapping(value = "/submit")
    public ModelAndView submit(HttpServletRequest request) {
        //如果登录成功，转到index页面
        ModelAndView mv = new ModelAndView("/index/login/show");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LeUser user = this.userService.findUserByEmail(email);
        if (user == null) {
            //Email地址错误
            mv.addObject(Const.FAILURE, true);
            mv.addObject(Const.MESSAGE, "该邮箱尚未注册");
            return mv;
        }
        //比对密码是否正确
        String origionPassword = user.getPassword();
        if (!CipherUtil.validatePassword(origionPassword, password)) {
            //密码错误
            mv.addObject(Const.FAILURE, true);
            mv.addObject(Const.MESSAGE, "密码错误");
            return mv;
        }
        request.getSession().setAttribute("email", email);
        return new ModelAndView(new RedirectView("/lefoto/index/show.html"));
    }
}