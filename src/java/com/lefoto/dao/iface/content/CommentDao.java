/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.iface.content;

import com.lefoto.model.content.LeComment;

/**
 *
 * @author Eric
 */
public interface CommentDao {

    public void addComment(LeComment comment);

    public void delComment(LeComment comment);

    public LeComment findCommentById(int id);

}
