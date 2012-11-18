/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.iface.content;

import com.lefoto.model.content.LeTopic;

public interface TopicService {

    public void addTopic(LeTopic topic);

    public void delTopic(LeTopic topic);

    public LeTopic findTopicById(int id);
}
