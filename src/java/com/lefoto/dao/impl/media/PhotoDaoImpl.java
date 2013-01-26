/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.impl.media;

import com.lefoto.common.cache.PhotoCache;
import com.lefoto.dao.iface.media.PhotoDao;
import com.lefoto.model.media.LePhoto;
import com.lefoto.model.media.LePhotoUp;
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
        //从缓存中删除图片
        PhotoCache.removePhoto(photo);
    }

    @Override
    public void updatePhoto(LePhoto photo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(photo);
        session.getTransaction().commit();
        //更新缓存
        PhotoCache.updatePhoto(photo);
    }

    @Override
    public void addPhotoUp(LePhotoUp photoUp) {
        LePhoto photo = this.findPhotoById(photoUp.getPhotoId());
        if (photo == null) {
            return;
        }
        photo.setUpCount(photo.getUpCount() + 1);
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(photoUp);
        session.merge(photo);
        session.getTransaction().commit();
        //更新内存
        PhotoCache.addPhotoUp(photoUp);
        PhotoCache.updatePhoto(photo);

    }

    @Override
    public void cancelUpPhoto(LePhotoUp photoUp) {
        LePhoto photo = this.findPhotoById(photoUp.getPhotoId());
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(photoUp);
        if (photo != null) {
            photo.setUpCount(photo.getUpCount() - 1);
            session.merge(photo);
        }
        session.getTransaction().commit();
        //更新内存
        PhotoCache.removePhotoUp(photoUp);
        PhotoCache.updatePhoto(photo);

    }

    ////////////////////////查询////////////////////////////
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
    public LePhotoUp findPhotoUp(int photoId, int userId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LePhotoUp.class);
        criteria.add(Restrictions.eq("photoId", photoId));
        criteria.add(Restrictions.eq("userId", userId));
        List photoUps = criteria.list();
        session.getTransaction().commit();
        if (photoUps != null && !photoUps.isEmpty()) {
            return (LePhotoUp) photoUps.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<LePhotoUp> findPhotoUps(int photoId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LePhotoUp.class);
        criteria.add(Restrictions.eq("photoId", photoId));
        List photoUps = criteria.list();
        session.getTransaction().commit();
        if (photoUps != null && !photoUps.isEmpty()) {
            return photoUps;
        } else {
            return null;
        }
    }

    @Override
    public List getPhotos(int cateId, int lastPhotoId, int size) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LePhoto.class);
        if (cateId != 0) {
            criteria.add(Restrictions.eq("categoryId", cateId));
        }
        if (lastPhotoId != 0) {
            criteria.add(Restrictions.le("id", lastPhotoId));
        }
        if (size != 0) {
            if (size > 50) {
                size = 50;
            }
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

    @Override
    public List getPhotosByAdmin(int cateId, int size, Integer[] types) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LePhoto.class);
        criteria.add(Restrictions.eq("type", 1));
        criteria.add(Restrictions.in("type", types));
        if (cateId != 0) {
            criteria.add(Restrictions.eq("categoryId", cateId));
        }
        if (size == 0) {
            size = 10000;
        }
        criteria.setMaxResults(size);
        criteria.addOrder(Order.asc("id"));
        List photos = criteria.list();
        session.getTransaction().commit();
        if (photos != null && !photos.isEmpty()) {
            return photos;
        } else {
            return null;
        }
    }

    @Override
    public List<LePhotoUp> getAllPhotoUps() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LePhotoUp.class);
        List ups = criteria.list();
        session.getTransaction().commit();
        if (ups != null && !ups.isEmpty()) {
            return ups;
        } else {
            return null;
        }
    }
    ////////////////////////查询////////////////////////////
}
