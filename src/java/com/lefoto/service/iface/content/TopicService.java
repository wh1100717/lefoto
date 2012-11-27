/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.iface.content;

import com.lefoto.model.content.LeTopic;

/**
 * 微博的Service类
 * unused
 * @author Eric
 */
public interface TopicService {

    /**
     * 添加主题
     * @param topic
     */
    public void addTopic(LeTopic topic);

    /**
     * 删除主题
     * @param topic
     */
    public void delTopic(LeTopic topic);

    /**
     * 根据Id获取主题
     * @param id
     * @return
     */
    public LeTopic findTopicById(int id);
}
