/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.backManage;

import com.lefoto.common.base.BaseController;
import com.lefoto.common.base.Const;
import com.lefoto.model.media.LeCategory;
import com.lefoto.service.iface.media.CategoryService;
import freemarker.template.Configuration;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Eric
 */
@Controller
@RequestMapping("/back/category")
public class CategoryManageController extends BaseController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/add")
    public ModelAndView index(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("/back/categoryManage");
        String cateName = this.getParaStringFromRequest("cateName");
        String message = "";
        LeCategory category = categoryService.findCategoryByName(cateName);
        if (category != null) {
            message = "该类名已经使用";
            mv.addObject("message", message);
        } else {
            category = new LeCategory();
            category.setName(cateName);
            categoryService.addCategory(category);
        }
        List<LeCategory> categories = categoryService.findCategories();
        mv.addObject("categories", categories);
        return mv;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cateName = this.getParaStringFromRequest("cateName");
        LeCategory category = categoryService.findCategoryByName(cateName);
        if (category == null) {
            response.getWriter().write("该分类不存在或已被删除");
        } else {
            categoryService.deleteCategotry(category);
            response.getWriter().write(Const.SUCCESS);
        }
    }
}
