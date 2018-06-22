package com.example.owner.unsplashclient.model.dto;

import com.example.owner.unsplashclient.model.entity.UnsplashEntity;

import java.util.List;

import io.reactivex.Observable;

public interface UnsplashPhotoModel {

    Observable<List<UnsplashEntity>> getPhotosStat(int page);
}
