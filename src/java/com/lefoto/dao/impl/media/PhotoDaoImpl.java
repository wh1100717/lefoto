/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.impl.media;

import com.lefoto.dao.iface.media.PhotoDao;
import com.lefoto.model.media.LePhoto;
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

    @Override
    public LePhoto findPhotoById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LePhoto.class);
        criteria.add(Restrictions.eq("id", id));
        List photos = criteria.list();
        session.getTransaction().commit();
        if (photos != null && !photos.isEmpty()) {
            return (LePhoto) photos.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<LePhoto> findPhotosByAlbumId(int albumId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LePhoto.class);
        criteria.add(Restrictions.eq("albumId", albumId));
        List photos = criteria.list();
        session.getTransaction().commit();
        if (photos != null && !photos.isEmpty()) {
            return photos;
        } else {
            return null;
        }
    }

    @Override
    public List<LePhoto> findPhotosByUserId(int userId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LePhoto.class);
        criteria.add(Restrictions.eq("userId", userId));
        List photos = criteria.list();
        session.getTransaction().commit();
        if (photos != null && !photos.isEmpty()) {
            return photos;
        } else {
            return null;
        }
    }
}
