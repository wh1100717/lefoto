/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.index;

import com.lefoto.common.base.Const;
import com.lefoto.common.filter.LoadInfoServlet;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Eric
 */
public class AdminController {

    @Autowired
    private LoadInfoServlet loadInfoServlet;

    @RequestMapping(value = "/admin/synPhotoCache")
    public @ResponseBody
    String synPhotoCache(HttpServletRequest request) throws IOException {
        loadInfoServlet.init();
        return Const.SUCCESS;
    }
}
