package org.test.models;

public interface Response<T> {
    T getData();

    String getMessage();

    ResponseStatus getStatusCode();
}
