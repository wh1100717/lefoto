/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.impl.media;

import com.lefoto.dao.iface.media.AlbumDao;
import com.lefoto.model.media.LeAlbum;
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
@Repository("albumDao")
@Transactional
public class AlbumDaoImpl implements AlbumDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addAlbum(LeAlbum album) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(album);
        session.getTransaction().commit();
    }

    @Override
    public void deleteAlbum(LeAlbum album) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(album);
        session.getTransaction().commit();
    }

    @Override
    public void updateAlbum(LeAlbum album) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(album);
        session.getTransaction().commit();
    }

    @Override
    public LeAlbum findAlbumById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeAlbum.class);
        criteria.add(Restrictions.eq("id", id));
        List albums = criteria.list();
        session.getTransaction().commit();
        if (albums != null && !albums.isEmpty()) {
            return (LeAlbum) albums.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<LeAlbum> findAlbumsByUserId(int userId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeAlbum.class);
        criteria.add(Restrictions.eq("user_id", userId));
        List albums = criteria.list();
        session.getTransaction().commit();
        if (albums != null && !albums.isEmpty()) {
            return albums;
        } else {
            return null;
        }
    }

    @Override
    public LeAlbum findUserAlbumByName(String name, int userId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeAlbum.class);
        criteria.add(Restrictions.eq("user_id", userId));
        criteria.add(Restrictions.eq("name", name));
        List albums = criteria.list();
        session.getTransaction().commit();
        if (albums != null && !albums.isEmpty()) {
            return (LeAlbum) albums.get(0);
        } else {
            return null;
        }
    }
}
