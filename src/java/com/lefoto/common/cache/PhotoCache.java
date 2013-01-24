/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.cache;

import com.lefoto.common.base.Const;
import com.lefoto.common.utils.RandomUtil;
import com.lefoto.model.media.LePhoto;
import com.lefoto.model.media.LePhotoUp;
import com.lefoto.service.iface.media.PhotoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */
public class PhotoCache {
    //1代表搞笑 | 2代表萌宠 | 3代表童真 | 4代表美女 | 5代表漫画

    static List<List<LePhoto>> photoList = new ArrayList<List<LePhoto>>(6);
    static List<LePhotoUp> photoUpList = new ArrayList<LePhotoUp>();

    static public String initPhotoList(PhotoService photoService) {
        photoList.add(photoService.getPhotosByAdmin(0, Const.MAX_PHOTO_CACHE_RECORDS));
        photoList.add(photoService.getPhotosByAdmin(1, Const.MAX_PHOTO_CACHE_RECORDS));
        photoList.add(photoService.getPhotosByAdmin(2, Const.MAX_PHOTO_CACHE_RECORDS));
        photoList.add(photoService.getPhotosByAdmin(3, Const.MAX_PHOTO_CACHE_RECORDS));
        photoList.add(photoService.getPhotosByAdmin(4, Const.MAX_PHOTO_CACHE_RECORDS));
        photoList.add(photoService.getPhotosByAdmin(5, Const.MAX_PHOTO_CACHE_RECORDS));
        photoUpList = photoService.getAllPhotoUps();
        return Const.SUCCESS;
    }

    static public LePhotoUp findPhotoUp(int photoId, int userId) {
        for (LePhotoUp up : photoUpList) {
            if (up.getPhotoId() == photoId && up.getUserId() == userId) {
                return up;
            }
        }
        return null;
    }

    static public LePhotoUp findPhotoUp(LePhotoUp up) {
        int photoId = up.getPhotoId();
        int userId = up.getUserId();
        return findPhotoUp(photoId, userId);
    }

    static public List<LePhotoUp> findPhotoUps(int photoId) {
        List<LePhotoUp> result = new ArrayList<LePhotoUp>();
        for (LePhotoUp up : photoUpList) {
            if (up.getPhotoId() == photoId) {
                result.add(up);
            }
        }
        return result;
    }

    static public void addPhotoUp(LePhotoUp up) {
        if (findPhotoUp(up) == null) {
            photoUpList.add(up);
        }
    }

    static public void removePhotoUp(LePhotoUp up) {
        int photoId = up.getPhotoId();
        int userId = up.getUserId();
        for (int index = 0; index < photoUpList.size(); index++) {
            LePhotoUp photoUp = photoUpList.get(index);
            if (photoUp.getPhotoId() == photoId && photoUp.getUserId() == userId) {
                photoUpList.remove(photoUp);
                break;
            }
        }
    }

    static public String addPhoto(LePhoto photo) {
        photoList.get(0).add(photo);
        photoList.get(photo.getCategoryId()).add(photo);
        return Const.SUCCESS;
    }

    static public String removePhoto(LePhoto photo) {
        List<LePhoto> photos = photoList.get(photo.getCategoryId());
        List<LePhoto> allPhotos = photoList.get(0);
        if (photos != null) {
            photos.remove(photo);
            allPhotos.remove(photo);
            return Const.SUCCESS;
        } else {
            return Const.FAILURE;
        }
    }

    static public List<LePhoto> getPhotos(int cateId, int lastPhotoId, int size, int type) {
        //type : 0表示按时间顺序排序 | 1表示按热度排序 | 2便是随便看看  也就是随机排序
        int count = 0;
        List<LePhoto> photos = photoList.get(cateId);
        if (photos == null) {
            return null;
        }
        List<LePhoto> result = new ArrayList<LePhoto>();
        if (type == 0) {
            boolean flag = true;
            if (lastPhotoId == 0) {
                flag = false;
            }
            for (int index = photos.size() - 1; index >= 0; index--) {
                LePhoto photo = photos.get(index);
                if (flag) {
                    if (photo.getId() == lastPhotoId) {
                        flag = false;
                        continue;
                    }
                    continue;
                }
                result.add(photo);
                count++;
                if (count >= size) {
                    return result;
                }
            }
        }
        if (type == 2) {
            int amount = photos.size();
            int[] photoIds = RandomUtil.getUnDuplicateNums(size, 0, amount);
            int pointer = 0;
            int pointerNum = photoIds[pointer];
            for (int index = 0; index < amount; index++) {
                LePhoto photo = photos.get(index);
                if (pointerNum == index) {
                    result.add(photo);
                    pointer++;
                    if (pointer >= size) {
                        return result;
                    } else {
                        pointerNum = photoIds[pointer];
                    }
                }
            }
        }
        return null;
    }

    public static LePhoto getPhotoById(int photoId, int cateId) {
        List<LePhoto> photos = photoList.get(cateId);
        for (LePhoto photo : photos) {
            if (photo.getId() == photoId) {
                return photo;
            }
        }
        return null;
    }

    public static void updatePhoto(LePhoto photo) {
        int cateId = photo.getCategoryId();
        List<LePhoto> photos = photoList.get(cateId);
        if (photos == null) {
            return;
        }
        for (int index = 0; index < photos.size(); index++) {
            LePhoto lePhoto = photos.get(index);
            if (lePhoto.getId() == photo.getId()) {
                photos.set(index, photo);
                return;
            }
        }
    }
}
