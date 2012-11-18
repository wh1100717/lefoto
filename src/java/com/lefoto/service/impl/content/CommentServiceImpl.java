/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.impl.content;

import com.lefoto.dao.iface.content.CommentDao;
import com.lefoto.model.content.LeComment;
import com.lefoto.service.iface.content.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图片Service的实现类
 *
 * @author Eric
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public void addComment(LeComment comment) {
        commentDao.addComment(comment);
    }

    @Override
    public void delComment(LeComment comment) {
        commentDao.delComment(comment);
    }

    @Override
    public LeComment findCommentById(int id) {
        return commentDao.findCommentById(id);
    }
}
