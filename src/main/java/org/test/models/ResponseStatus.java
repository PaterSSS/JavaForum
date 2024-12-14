package org.test.models;

public enum ResponseStatus {
    OK(200),
    NotFound(404),
    InternalServerError(500);

    private final int code;

    ResponseStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
