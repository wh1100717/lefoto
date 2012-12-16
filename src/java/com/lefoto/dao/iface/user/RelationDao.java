/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.iface.user;

import com.lefoto.model.user.LeUser;
import com.lefoto.model.user.LeRelationGroup;
import com.lefoto.model.user.LeRelationship;
import java.util.List;

/**
 *
 * @author Eric
 */
public interface RelationDao {
    
    public String addGroup(LeUser user, String groupName);

    public boolean addFollowing(LeUser user, LeUser followingUser, LeRelationGroup group);

    public void removeFollowing(LeUser user, LeUser followeringUser);
    
    public void removeFollower(LeUser user, LeUser followerUser);
    
     public List<LeRelationship> findAllRelationships();
    
    public LeRelationship findFollowingRelation(LeUser user, LeUser followingUser);
    
    public List findGroups(LeUser user);
}
