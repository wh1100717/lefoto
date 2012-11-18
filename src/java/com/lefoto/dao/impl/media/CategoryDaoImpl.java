/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.impl.media;

import com.lefoto.dao.iface.media.CategoryDao;
import com.lefoto.model.media.LeCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Eric
 */
@Repository("categoryDao")
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addCategory(LeCategory category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(category);
        session.getTransaction().commit();
    }

    @Override
    public void deleteCategory(LeCategory category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(category);
        session.getTransaction().commit();
    }
}
