/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.index;

import com.lefoto.common.base.BaseController;
import com.lefoto.common.base.Const;
import com.lefoto.common.utils.CipherUtil;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.user.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 注册模块
 *
 * @author Eric
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {

    @Autowired
    private UserService userService;

    //渲染注册页面
    @RequestMapping(value = "/show")
    public ModelAndView show(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/index/register/show");
        return mv;
    }

    //处理注册请求
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("/index/register/add");
        String email = request.getParameter("email");
        String nickName = request.getParameter("nickName");
        String password = request.getParameter("password");
        String passconf = request.getParameter("passconf");
        //检查邮箱格式是否错误
        if (!this.getFormatUtil().checkEmailFormat(email)) {
            mv.addObject(Const.FAILURE, true);
            mv.addObject(Const.MESSAGE, "邮箱格式错误");
            return mv;
        }
        //检查密码格式是否错误，并比对两次输入密码是否相同
        String passwordFormat = this.getFormatUtil().checkPasswordFormat(password, passconf);
        if (passwordFormat.equals("passconfError")) {
            //两次输入的密码不一致
            mv.addObject(Const.FAILURE, true);
            mv.addObject(Const.MESSAGE, "两次输入的密码不一致");
            return mv;
        } else if (passwordFormat.equals("lengthError")) {
            //密码长度小于六位
            mv.addObject(Const.FAILURE, true);
            mv.addObject(Const.MESSAGE, "密码长度小于6");
            return mv;
        }
        //检查邮箱是否已经注册
        if (this.userService.checkEmailExist(email)) {
            //该邮箱已被注册
            mv.addObject(Const.FAILURE, true);
            mv.addObject(Const.MESSAGE, "邮箱已被注册");
            return mv;
        }
        //密码加密
        password = CipherUtil.generatePassword(passconf);
        LeUser user = new LeUser();
        user.setEmail(email);
        user.setPassword(password);
        user.setNickName(nickName);
        this.userService.addUser(user);

        request.getSession().setAttribute("user", user);
        return mv;
    }
}
