/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.iface.content;

import com.lefoto.model.content.LeTopic;

/**
 *
 * @author Eric
 */
public interface TopicDao {

    public void addTopic(LeTopic topic);

    public void delTopic(LeTopic topic);

    public LeTopic findTopicById(int id);

}
