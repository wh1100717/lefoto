/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.impl.media;

import com.lefoto.dao.iface.media.CategoryDao;
import com.lefoto.model.media.LeCategory;
import com.lefoto.service.iface.media.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eric
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void addCategory(LeCategory category) {
        categoryDao.addCategory(category);
    }

    @Override
    public void deleteCategotry(LeCategory category) {
        categoryDao.deleteCategory(category);
    }

    @Override
    public LeCategory findCategoryById(int id) {
        return categoryDao.findCategoryById(id);
    }

    @Override
    public LeCategory findCategoryByName(String name) {
        return categoryDao.findCategoryByName(name);
    }

    @Override
    public List<LeCategory> findCategories() {
        return categoryDao.findCategories();
    }

}
