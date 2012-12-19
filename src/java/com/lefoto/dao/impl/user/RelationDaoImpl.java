/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.impl.user;

import com.lefoto.dao.iface.user.RelationDao;
import com.lefoto.model.user.LeRelationGroup;
import com.lefoto.model.user.LeRelationship;
import com.lefoto.model.user.LeUser;
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
    public boolean addFollowing(LeUser user, LeUser followingUser, LeRelationGroup group) {
        //user为本人, followingUser为要关注的人

        //首先查看followingUser是否已经是user的粉丝
        //如果已经是粉丝了，则直接更改他们已经存在的relationship中的relationType为2
        //如果还不是粉丝，则创建一个relationship
        LeRelationship followerRelation = this.findFollowingRelation(followingUser, user);
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        //relationType: 1表示单纯的关注关系， 2表示相互关注关系
        //当不存在好友关系时

        if (followerRelation != null) {
            followerRelation.setRelationType(2);
            session.saveOrUpdate(followerRelation);
        } else {
            LeRelationship followingRelationship = new LeRelationship();
            followingRelationship.setUserId(user.getId());
            followingRelationship.setFollowingUserId(followingUser.getId());
            followingRelationship.setCreateUserId(user.getId());
            followingRelationship.setGroupId(group.getId());
            session.saveOrUpdate(followingRelationship);
        }
        session.getTransaction().commit();
        return true;
    }

    /**
     * 取消关注~ 当为单纯关注的时候，直接删除relationship；
     * 当其为互相关注的时候，删除relationship,并更新对方的relationship,即followRelation，的relationType为1
     *
     * @param user
     * @param followUser
     */
    @Override
    public void removeFollowing(LeUser user, LeUser followingUser) {
        LeRelationship followingRelationship = this.findFollowingRelation(user, followingUser);
        LeRelationship followerRelation = this.findFollowingRelation(followingUser, user);
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        if (followingRelationship != null) {
            session.delete(followingRelationship);
        } else {
            if (followerRelation != null) {
                followerRelation.setRelationType(1);
                session.saveOrUpdate(followerRelation);
            }
        }
        session.getTransaction().commit();
    }

    /**
     * 取消粉丝~取消双方的好友关系
     *
     * @param user
     * @param followUser
     */
    @Override
    public void removeFollower(LeUser user, LeUser followerUser) {
        LeRelationship followingRelationship = this.findFollowingRelation(user, followerUser);
        LeRelationship followerRelation = this.findFollowingRelation(followerUser, user);
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        if (followingRelationship != null) {
            session.delete(followingRelationship);
        } else if (followerRelation != null) {
            session.delete(followerRelation);
        }
        session.getTransaction().commit();
    }

    /**
     * 获取所有的关系记录
     *
     * @return
     */
    @Override
    public List<LeRelationship> findAllRelationships() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeRelationship.class);
        List relationships = criteria.list();
        session.getTransaction().commit();
        if (relationships != null && !relationships.isEmpty()) {
            return relationships;
        } else {
            return null;
        }
    }

    /**
     * 获取userId为user.getId. followingUserId为followingUser.getId的记录
     *
     * @param user
     * @param followingUser
     * @return
     */
    @Override
    public LeRelationship findFollowingRelation(LeUser user, LeUser followingUser) {
        Session session = this.sessionFactory.getCurrentSession();
        LeRelationship relationship = null;
        session.beginTransaction();
        int userId = user.getId();
        int followingUserId = followingUser.getId();
        Criteria criteria = session.createCriteria(LeRelationship.class);
        criteria.add(Restrictions.eq("userId", userId));
        criteria.add(Restrictions.eq("followingUserId", followingUserId));
        List relationshipList = criteria.list();
        if (relationshipList != null && !relationshipList.isEmpty()) {
            relationship = (LeRelationship) relationshipList.get(0);
        }
        session.getTransaction().commit();
        return relationship;
    }

    @Override
    public String addGroup(LeUser user, String groupName) {
        List relationGroups = this.findGroups(user);
        for (Iterator it = relationGroups.iterator(); it.hasNext();) {
            LeRelationGroup group = (LeRelationGroup) it.next();
            if (group.getName().equals(groupName)) {
                return "error";
            }
        }
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        LeRelationGroup relationGroup = new LeRelationGroup();
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
        Criteria criteria = session.createCriteria(LeRelationGroup.class);
        criteria.add(Restrictions.eq("userId", user.getId()));
        List relationGroups = criteria.list();
        session.getTransaction().commit();
        return relationGroups;
    }

}
