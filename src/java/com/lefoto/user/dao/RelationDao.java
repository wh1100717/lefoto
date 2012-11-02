/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.user.dao;

import com.lefoto.user.model.LeUser;
import com.lefoto.user.model.RelationGroup;
import com.lefoto.user.model.Relationship;
import java.util.List;

/**
 *
 * @author Eric
 */
public interface RelationDao {
    
    public String addGroup(LeUser user, String groupName);

    public void addFollow(LeUser user, LeUser followUser, RelationGroup group);

    public void removeFollow(LeUser user, LeUser followUser);
    
    public void removeFans(LeUser user, LeUser followUser);
    
    public Relationship findRelation(LeUser user, LeUser followUser);
    
    public List findGroups(LeUser user);
}
