/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.iface.media;

import com.lefoto.model.media.LeCategory;
import java.util.List;

/**
 *
 * @author Eric
 */
public interface CategoryDao {

    public void addCategory(LeCategory category);

    public void deleteCategory(LeCategory category);

    public LeCategory findCategoryById(int id);

    public LeCategory findCategoryByName(String name);

    public List<LeCategory> findCategories();
}
