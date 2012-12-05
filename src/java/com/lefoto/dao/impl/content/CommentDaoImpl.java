/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.impl.content;

import com.lefoto.dao.iface.content.CommentDao;
import com.lefoto.model.content.LeComment;
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
@Repository("commentDao")
@Transactional
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addComment(LeComment comment) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(comment);
        session.getTransaction().commit();
    }

    @Override
    public void delComment(LeComment comment) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(comment);
        session.getTransaction().commit();
    }

    @Override
    public LeComment findCommentById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeComment.class);
        criteria.add(Restrictions.eq("id", id));
        List comments = criteria.list();
        session.getTransaction().commit();
        if (comments != null && !comments.isEmpty()) {
            return (LeComment) comments.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<LeComment> getComments(int objectType, int objectId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeComment.class);
        criteria.add(Restrictions.eq("objectType", objectType));
        criteria.add(Restrictions.eq("objectId", objectId));
        List comments = criteria.list();
        session.getTransaction().commit();
        if (comments != null && !comments.isEmpty()) {
            return comments;
        } else {
            return null;
        }
    }
}
