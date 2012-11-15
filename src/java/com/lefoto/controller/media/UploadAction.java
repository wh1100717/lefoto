/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.media;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Eric
 */
@Controller
public class UploadAction {

    @RequestMapping(value = "/media/upload")
    public ModelAndView show(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/index/login/show");
        return mv;
    }
}
