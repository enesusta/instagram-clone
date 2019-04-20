package com.enesusta.instagramclone.activities;

public class User {

    public String takim,numara,isim,yas;

    public User(String takim, String numara, String isim, String yas) {
        this.takim = takim;
        this.numara = numara;
        this.isim = isim;
        this.yas = yas;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "takim='" + takim + '\'' +
                ", numara='" + numara + '\'' +
                ", isim='" + isim + '\'' +
                ", yas='" + yas + '\'' +
                '}';
    }
}
