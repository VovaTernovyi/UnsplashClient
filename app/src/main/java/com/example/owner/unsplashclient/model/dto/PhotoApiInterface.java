package com.example.owner.unsplashclient.model.dto;

import android.arch.lifecycle.LiveData;

import com.example.owner.unsplashclient.model.entity.UnsplashEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotoApiInterface {

    @GET("/photos")
    List<LiveData<UnsplashEntity>> getPhotos(@Query("client_id") String clientId);
}
