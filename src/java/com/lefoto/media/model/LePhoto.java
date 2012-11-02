/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.media.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Eric
 */
@Entity
@Table(name = "le_photo")
public class LePhoto implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;
    
    @Column(name = "type")
    String type;
    
    @Column(name = "file_size")
    int fileSize;
    
    @Column(name = "width")
    int width;
    
    @Column(name = "height")
    int height;
    
    @Column(name = "user_id")
    int userId;
    
    @Column(name = "user_name")
    String userName;
    
    @Column(name = "description")
    String description;
    
    @Column(name = "url")
    String url;
    
    @Column(name = "topicId")
    int topicId;
}
