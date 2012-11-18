/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.iface.media;

import com.lefoto.model.media.LeCategory;

/**
 *
 * @author Eric
 */
public interface CategoryDao {

    public void addCategory(LeCategory category);

    public void deleteCategory(LeCategory category);
}
