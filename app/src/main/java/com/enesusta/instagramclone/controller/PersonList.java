package com.enesusta.instagramclone.controller;

import com.enesusta.instagramclone.model.User;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class PersonList {

    @Getter
    private List<User> userList = new ArrayList<>();

    private PersonList() {

    }

    private static final PersonList instance = new PersonList();

    public void add(User user) {
        userList.add(user);
    }

    public static PersonList getInstance() {
        return instance;
    }

}
