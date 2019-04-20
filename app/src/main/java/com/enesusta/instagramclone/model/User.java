package com.enesusta.instagramclone.model;

import com.enesusta.instagramclone.controller.crypt.Crpyt;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

/*
 * @author : Enes Usta
 */


public class User implements Serializable {

    private String personEmail;
    private String personId;
    private String personPassword;
    private Crpyt crpyt;

    private static volatile User intance = null;
    private static Object LOCK = new Object();

    private User() {

    }

    public static User getInstance() {
        if (intance == null) {
            synchronized (LOCK) {
                intance = new User();
            }
        }
        return intance;
    }


    @Exclude
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        crpyt = new Crpyt(personPassword);
        this.personPassword = crpyt.getPass();
    }


}
