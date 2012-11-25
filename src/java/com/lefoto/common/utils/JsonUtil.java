package com.lefoto.common.utils;

import org.stringtree.json.JSONReader;
public class JsonUtil {
    public static Object getMap4Json(String jsonString){
    	JSONReader reader = new JSONReader();
    	Object obj = reader.read(jsonString);
        return obj;
    }
}