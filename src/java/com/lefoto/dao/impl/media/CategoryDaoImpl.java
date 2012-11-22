/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.impl.media;

import com.lefoto.dao.iface.media.CategoryDao;
import com.lefoto.model.media.LeCategory;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

    @Override
    public LeCategory findCategoryById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeCategory.class);
        criteria.add(Restrictions.eq("id", id));
        List categories = criteria.list();
        session.getTransaction().commit();
        if (categories != null && !categories.isEmpty()) {
            return (LeCategory) categories.get(0);
        } else {
            return null;
        }
    }

    @Override
    public LeCategory findCategoryByName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeCategory.class);
        criteria.add(Restrictions.eq("name", name));
        List categories = criteria.list();
        session.getTransaction().commit();
        if (categories != null && !categories.isEmpty()) {
            return (LeCategory) categories.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<LeCategory> findCategories() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeCategory.class);
        List categories = criteria.list();
        session.getTransaction().commit();
        if (categories != null && !categories.isEmpty()) {
            return categories;
        } else {
            return null;
        }
    }
}
