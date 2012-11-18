/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.impl.media;

import com.lefoto.dao.iface.media.AlbumDao;
import com.lefoto.model.media.LeAlbum;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
