/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.impl.content;

import com.lefoto.dao.iface.content.TopicDao;
import com.lefoto.model.content.LeTopic;
import com.lefoto.service.iface.content.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图片Service的实现类
 * @author Eric
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicDao;

    @Override
    public void addTopic(LeTopic topic) {
        topicDao.addTopic(topic);
    }

    @Override
    public void delTopic(LeTopic topic) {
        topicDao.delTopic(topic);
    }

    @Override
    public LeTopic findTopicById(int id) {
        return topicDao.findTopicById(id);
    }

   

}
