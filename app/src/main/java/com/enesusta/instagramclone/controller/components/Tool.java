package com.enesusta.instagramclone.controller.components;

import android.widget.TextView;

public interface Tool {

    default String toChar(TextView textView) {
        return textView.getText().toString();
    }

}
