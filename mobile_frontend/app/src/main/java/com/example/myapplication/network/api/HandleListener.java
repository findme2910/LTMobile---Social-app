package com.example.myapplication.network.api;

public interface HandleListener<T> {
    void onSuccess(T t);

    void onFailure(String errorMessage);
}
