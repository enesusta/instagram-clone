package com.enesusta.instagramclone.controller.components;

import android.widget.TextView;

public interface Tool {

    default String toChar(TextView textView) {
        return textView.getText().toString();
    }
    default String toString(Integer value) {
        return String.valueOf(value);
    }

}
