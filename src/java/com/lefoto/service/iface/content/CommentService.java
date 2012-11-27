/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.iface.content;

import com.lefoto.model.content.LeComment;

/**
 * 评论的Service类
 * @author Eric
 */
public interface CommentService {

    /**
     * 添加回复
     * @param comment
     */
    public void addComment(LeComment comment);

    /**
     * 删除回复
     * @param comment
     */
    public void delComment(LeComment comment);

    /**
     * 根据Id获取回复
     * @param id
     * @return
     */
    public LeComment findCommentById(int id);
}
