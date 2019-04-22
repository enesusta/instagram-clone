package com.enesusta.instagramclone.controller;

import java.util.HashMap;
import java.util.Map;

/*
 * @author : Enes Usta
 */

public class Pointer {

    private static Map<String,Object> map = new HashMap<>();

    public static void putObject(String string, Object object) {
        map.put(string,object);
    }

    public static Object getObject(String str) {
        return map.get(str);
    }

    public static void removeObject(String string) {
        map.remove(string);
    }

    public static Map<String,Object> getMap() {
        return map;
    }



}
