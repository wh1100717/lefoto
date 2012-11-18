/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.iface.content;

import com.lefoto.model.content.LeComment;

public interface CommentService {

    public void addComment(LeComment comment);

    public void delComment(LeComment comment);

    public LeComment findCommentById(int id);
}
