package com.lefoto.common.base;

public class Const {

    //Response Information
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String MESSAGE = "message";
    public static final String STATUS = "status";
    
    //Default
    public static final int DEFAULT_CATEGORY_ID = 2;
    public static final int DEFAULT_BROWSE_TYPE = 2;
    
    //Cache
    public static final int MAX_PHOTO_CACHE_RECORDS = 20000;
    
    //Resource
    public static final String RESOURCE_DIR = "";
    
    //Request
    public static final String SESSION_ID = "sessionId";
    public static final String USER_ID = "userId";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "email";
    public static final String USER = "user";
    public static final int COOKIE_EXPIRE_TIME = 86400;//1 * 24 * 3600
    
    //Upload
    public static String UPLOAD_FOLDER_PATH = "/WEB-INF/upload";
    
    //Populize
    public static String DEFAULT_USER_NAME_PATH = "D:/NBWS/lefoto/web/WEB-INF/userName.xml";
    public static String DEFAULT_USER_FACE_PATH = "D:/imgGrab/populize/face";
    public static int DEFAULT_USER_NAME_LINES = 7930;
    public static String PHOTO_POPULIZE_PATH = "D:/imgGrab/populize/pet";
}
