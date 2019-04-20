package com.enesusta.instagramclone.controller.firebase;

import com.enesusta.instagramclone.controller.LoginException;
import com.enesusta.instagramclone.model.User;

@FunctionalInterface
public interface InsertDAO {
    void execute(User user);
}
