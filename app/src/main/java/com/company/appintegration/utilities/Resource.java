package com.company.appintegration.utilities;

import androidx.annotation.NonNull;

public class Resource<T> {

    @NonNull
    public final Status status;
    @NonNull
    public final T data;
    @NonNull
    public final String message;

    public Resource(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg) {
        return new Resource<>(Status.ERROR, null, msg);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(Status.LOADING, null, null);
    }


}
