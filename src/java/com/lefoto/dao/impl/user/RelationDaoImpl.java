/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.impl.user;

import com.lefoto.dao.iface.user.RelationDao;
import com.lefoto.model.user.LeUser;
import com.lefoto.model.user.RelationGroup;
import com.lefoto.model.user.Relationship;
import java.util.Iterator;
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
@Repository("relationDao")
@Transactional
public class RelationDaoImpl implements RelationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addFollow(LeUser user, LeUser followUser, RelationGroup group) {
        //当不存在好友关系或者只存在followUser关注user的时候
        Relationship relationship = this.findRelation(user, followUser);
        Relationship followRelation = this.findRelation(followUser, user);
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        //relationType: 1表示单纯的关注关系， 2表示相互关注关系
        //当不存在好友关系时
        if (relationship == null) {
            relationship = new Relationship();
            relationship.setUserId(user.getId());
            relationship.setUserName(user.getName());
            relationship.setFollowUserId(followUser.getId());
            relationship.setFollowUserName(followUser.getName());
            relationship.setCreateUserId(user.getId());
            relationship.setGroupId(group.getId());
            relationship.setGroupName(group.getName());
            if (followRelation == null) {
                relationship.setRelationType(1);
            } else {
                //当存在user被followUser follow的时候
                followRelation.setRelationType(2);
                session.saveOrUpdate(followRelation);
                relationship.setRelationType(2);
            }
            session.saveOrUpdate(relationship);
        }
        session.getTransaction().commit();
    }

    /**
     * 取消关注~ 当为单纯关注的时候，直接删除relationship；
     * 当其为互相关注的时候，删除relationship,并更新对方的relationship,即followRelation，的relationType为1
     * @param user
     * @param followUser
     */
    @Override
    public void removeFollow(LeUser user, LeUser followUser) {
        Relationship relationship = this.findRelation(user, followUser);
        Relationship followRelation = this.findRelation(followUser, user);
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        if (relationship != null) {
            if (followRelation != null) {
                followRelation.setRelationType(1);
                session.saveOrUpdate(followRelation);
            }
            session.delete(relationship);
        }
        session.getTransaction().commit();
    }

    /**
     * 取消粉丝~取消对方对自己的关注
     * @param user
     * @param followUser
     */
    @Override
    public void removeFans(LeUser user, LeUser followUser) {
        Relationship relationship = this.findRelation(user, followUser);
        Relationship followRelation = this.findRelation(followUser, user);
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        if (relationship != null) {
            relationship.setRelationType(1);
            session.saveOrUpdate(relationship);
        }
        if (followRelation != null) {
            session.delete(followRelation);
        }
        session.getTransaction().commit();
    }

    @Override
    public Relationship findRelation(LeUser user, LeUser followUser) {
        Session session = this.sessionFactory.getCurrentSession();
        Relationship relationship = null;
        session.beginTransaction();
        int userId = user.getId();
        int followUserId = followUser.getId();
        Criteria criteria = session.createCriteria(Relationship.class);
        criteria.add(Restrictions.eq("userId", userId));
        criteria.add(Restrictions.eq("followUserId", followUserId));
        List relationshipList = criteria.list();
        if (relationshipList != null && !relationshipList.isEmpty()) {
            relationship = (Relationship) relationshipList.get(0);
        }
        session.getTransaction().commit();
        return relationship;
    }

    @Override
    public String addGroup(LeUser user, String groupName) {
        List relationGroups = this.findGroups(user);
        for (Iterator it = relationGroups.iterator(); it.hasNext();) {
            RelationGroup group = (RelationGroup) it.next();
            if (group.getName().equals(groupName)) {
                return "error";
            }
        }
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        RelationGroup relationGroup = new RelationGroup();
        relationGroup.setName(groupName);
        relationGroup.setUserId(user.getId());
        session.saveOrUpdate(relationGroup);
        session.getTransaction().commit();
        return groupName;
    }

    @Override
    public List findGroups(LeUser user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RelationGroup.class);
        criteria.add(Restrictions.eq("userId", user.getId()));
        List relationGroups = criteria.list();
        session.getTransaction().commit();
        return relationGroups;
    }
}
