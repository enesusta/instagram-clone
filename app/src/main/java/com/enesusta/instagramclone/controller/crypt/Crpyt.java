package com.enesusta.instagramclone.controller.crypt;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @author : Enes Usta
 */

@NoArgsConstructor
public class Crpyt {

    public String toHash(String pass) {
        return Hashing.sha256().hashString(pass, StandardCharsets.UTF_8).toString();
    }


}
