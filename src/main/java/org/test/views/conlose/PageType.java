package org.test.views.conlose;

public enum PageType {
    MAIN("mainPage"),
    PROFILE("profilePage"),
    CATEGORY("categoryPage"),
    POST("postPage"),
    LOGIN("loginPage"),
    CREATE_POST("createPostPage"),
    EXIT("exitPage");

    private final String value;

    PageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
