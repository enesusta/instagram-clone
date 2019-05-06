package com.enesusta.instagramclone.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/*
 * @author : Enes Usta
 */


public interface Time {

    default String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Istanbul"));
        return sdf.format(new Date()).toString();
    }


}
