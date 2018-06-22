package com.example.owner.unsplashclient;

import com.example.owner.unsplashclient.model.entity.UnsplashEntity;

import java.util.List;

public interface MyView {

    void showData(List<UnsplashEntity> entityList);

    void showError(String error);
}
