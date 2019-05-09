package com.enesusta.instagramclone.controller.firebase;

public class ContentManager {

    private ContentService contentService;

    public ContentManager(ContentService contentService) {
        this.contentService = contentService;
    }

    public void uploadContent() {
        contentService.uploadContent();
    }



}
