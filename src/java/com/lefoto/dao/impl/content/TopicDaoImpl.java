/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.impl.content;

import com.lefoto.dao.iface.content.TopicDao;
import com.lefoto.model.content.LeTopic;
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
@Repository("topicDao")
@Transactional
public class TopicDaoImpl implements TopicDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTopic(LeTopic topic) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(topic);
        session.getTransaction().commit();
    }

    @Override
    public void delTopic(LeTopic topic) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(topic);
        session.getTransaction().commit();
    }

    @Override
    public LeTopic findTopicById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeTopic.class);
        criteria.add(Restrictions.eq("id", id));
        List topics = criteria.list();
        session.getTransaction().commit();
        if (topics != null && !topics.isEmpty()) {
            return (LeTopic) topics.get(0);
        } else {
            return null;
        }
    }
}
