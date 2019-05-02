package com.enesusta.instagramclone.controller.firebase;

import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.model.User;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Share {

    protected User user = (User) Pointer.getObject("user");


}
