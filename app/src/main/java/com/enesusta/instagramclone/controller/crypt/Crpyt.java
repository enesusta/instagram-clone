package com.enesusta.instagramclone.controller.crypt;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

import lombok.Getter;
import lombok.Setter;

/*
 * @author : Enes Usta
 */

public class Crpyt {

    @Setter
    @Getter
    private String pass;

    public Crpyt(String pass) {
        setPass(toHash(pass));
    }

    public String toHash(String pass) {
        return Hashing.sha256().hashString(pass, StandardCharsets.UTF_8).toString();
    }



}
