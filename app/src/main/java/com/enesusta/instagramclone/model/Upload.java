package com.enesusta.instagramclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Upload {

    private String uploadName;
    private String uploadImageUrl;

    public Upload(String uploadName, String uploadImageUrl) {

        if (uploadName.trim().equals(""))
            uploadName = "No Name";

        this.uploadName = uploadName;
        this.uploadImageUrl = uploadImageUrl;
    }


}
