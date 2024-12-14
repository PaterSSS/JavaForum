package org.test.views.conlose;

public enum PageType {
    MAIN("mainPage"),
    PROFILE("profilePage"),
    CATEGORY("categoryPage"),
    POST("postPage"),
    LOGIN("loginPage"),
    EXIT("exitPage");

    private final String value;

    PageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
