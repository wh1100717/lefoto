/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.iface.content;

import com.lefoto.model.content.LeComment;
import java.util.List;

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

    /**
     * 获取某一类型对象的所有评论
     * @param objectType 0表示图片
     * @param objectId
     * @return
     */
    public List<LeComment> getComments(int objectType, int objectId);
    
    /**
     * Ajax获取某一类型对象的部分评论 按最近评论的顺序排序
     * @param objectType 0表示图片
     * @param objectId
     * @param lastCommentId 上一次获取的最后一条评论Id
     * @param size 需要获取的评论总条数
     * @return
     */
    public List<LeComment> getCommentsAjax(int objectType, int objectId, int lastCommentId, int size);
}
