package com.enesusta.instagramclone.controller.firebase;

import java.util.Map;

public class CommentManager {

    private CommentService contentService;

    public CommentManager(CommentService contentService) {
        this.contentService = contentService;
    }

    public void uploadContent() {
        contentService.uploadContent();
    }

    public Map getContent() {
        return contentService.getContent();
    }




}
