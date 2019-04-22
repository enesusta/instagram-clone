package com.enesusta.instagramclone.model;

import android.util.Log;

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
    private String personFullName;
    private String personUserName;
    private String personWebsite;
    private String personBio;
    private String personPhone;
    private String personGender;

    private Crpyt crpyt;


    public User() {

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
       /* crpyt = new Crpyt(personPassword);
        this.personPassword = crpyt.getPass();
        Log.d("passfirst", "setPersonPassword: " + crpyt.getPass());
        */
       this.personPassword = personPassword;
    }

    public String getPersonFullName() {
        return personFullName;
    }

    public void setPersonFullName(String personFullName) {
        this.personFullName = personFullName;
    }

    public String getPersonUserName() {
        return personUserName;
    }

    public void setPersonUserName(String personUserName) {
        this.personUserName = personUserName;
    }
}
