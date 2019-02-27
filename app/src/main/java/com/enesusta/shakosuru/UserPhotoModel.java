package com.enesusta.shakosuru;

public class UserPhotoModel extends UserModel {

    private int resimId;

    public UserPhotoModel(String name, String surname, int age, int resimIdd) {

        super(name, surname, age);
        this.resimId = resimIdd;

    }

    public int getResimId() {
        return resimId;
    }

    public void setResimId(int resimId) {
        this.resimId = resimId;
    }
}
