package com.enesusta.instagramclone.controller.enums;


public enum Priority {
    LOW(2),
    MEDIUM(1),
    HIGH(0);

    private int $;

    Priority(int $) {
        this.$ = $;
    }

    public int getPriority() {
        return $;
    }

}
