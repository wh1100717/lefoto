/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.iface.media;

import com.lefoto.model.media.LeAlbum;
import java.util.List;

/**
 * 相册基本操作的Service类
 * @author Eric
 */
public interface AlbumService {

    /**
     * 添加相册
     *
     * @param album
     */
    public void addAlbum(LeAlbum album);

    /**
     * 删除相册
     *
     * @param album
     */
    public void deleteAlbum(LeAlbum album);

    /**
     * 编辑相册
     *
     * @param album
     */
    public void updateAlbum(LeAlbum album);

    /**
     * 根据相册ID获取相册
     *
     * @param id
     * @return
     */
    public LeAlbum findAlbumById(int id);

    /**
     * 根据用户ID获取其相册列表
     *
     * @param userId
     * @return
     */
    public List<LeAlbum> findAlbumsByUserId(int userId);

    /**
     * 根据相册名称和用户ID获取该相册Bean
     * @param name
     * @param userId
     * @return
     */
    public LeAlbum findUserAlbumByName(String name, int userId);
}
