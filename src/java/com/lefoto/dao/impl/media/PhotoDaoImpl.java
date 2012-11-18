/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.impl.media;

import com.lefoto.dao.iface.media.PhotoDao;
import com.lefoto.model.media.LePhoto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Eric
 */
@Repository("photoDao")
@Transactional
public class PhotoDaoImpl implements PhotoDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPhoto(LePhoto photo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(photo);
        session.getTransaction().commit();
    }

    @Override
    public void deletePhoto(LePhoto photo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(photo);
        session.getTransaction().commit();
    }
}
