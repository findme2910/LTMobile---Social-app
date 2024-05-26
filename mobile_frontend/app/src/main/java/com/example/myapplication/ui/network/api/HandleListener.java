package com.example.myapplication.ui.network.api;

public interface HandleListener<T> {
    void onSuccess(T t);

    void onFailure(String errorMessage);
}
