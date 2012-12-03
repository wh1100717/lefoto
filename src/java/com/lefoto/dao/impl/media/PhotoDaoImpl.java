/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.impl.media;

import com.lefoto.dao.iface.media.PhotoDao;
import com.lefoto.model.media.LePhoto;
import com.lefoto.model.media.LePhotoUpdown;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
    public void updatePhoto(LePhoto photo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(photo);
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

    @Override
    public void upPhoto(int photoId, int userId) {
        LePhotoUpdown photoUpdown = new LePhotoUpdown();
        photoUpdown.setPhotoId(photoId);
        photoUpdown.setUserId(userId);
        photoUpdown.setType(1);
        this.addPhotoUpdown(photoUpdown);
    }

    @Override
    public void downPhoto(int photoId, int userId) {
        LePhotoUpdown photoUpdown = new LePhotoUpdown();
        photoUpdown.setPhotoId(photoId);
        photoUpdown.setUserId(userId);
        photoUpdown.setType(2);
        this.addPhotoUpdown(photoUpdown);
    }

    public void addPhotoUpdown(LePhotoUpdown photoUpdown) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(photoUpdown);
        session.getTransaction().commit();
    }

    @Override
    public LePhotoUpdown findPhotoUpdown(int photoId, int userId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LePhotoUpdown.class);
        criteria.add(Restrictions.eq("photoId", photoId));
        criteria.add(Restrictions.eq("userId", userId));
        List photoUpdowns = criteria.list();
        session.getTransaction().commit();
        if (photoUpdowns != null && !photoUpdowns.isEmpty()) {
            return (LePhotoUpdown) photoUpdowns.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List getPhotos(int cateId, int lastPhotoId, int size) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LePhoto.class);
        criteria.add(Restrictions.eq("categoryId", cateId));
        if (lastPhotoId != 0) {
            criteria.add(Restrictions.le("id", lastPhotoId));
        }
        if (size != 0) {
            criteria.setMaxResults(size);
        }
        criteria.addOrder(Order.desc("id"));
        List photos = criteria.list();
        session.getTransaction().commit();
        if (photos != null && !photos.isEmpty()) {
            return photos;
        } else {
            return null;
        }
    }
}
