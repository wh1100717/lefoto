/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.iface.media;

import com.lefoto.model.media.LeCategory;
import java.util.List;

/**
 * 分类的Service类
 * @author Eric
 */
public interface CategoryService {

    /**
     * 添加分类
     * @param category
     */
    public void addCategory(LeCategory category);

    /**
     * 删除分类
     * @param category
     */
    public void deleteCategotry(LeCategory category);
    
    /**
     * 根据Id获取分类
     * @param id
     * @return
     */
    public LeCategory findCategoryById(int id);
    
    /**
     * 根据名称获取分类
     * @param name
     * @return
     */
    public LeCategory findCategoryByName(String name);

    /**
     * 获取所有分类
     * @return
     */
    public List<LeCategory> findCategories();
}
