package com.example.owner.unsplashclient.model.api;

import com.example.owner.unsplashclient.model.entity.UnsplashEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotoApiInterface {

    @GET("/photos/")
    Observable<List<UnsplashEntity>> getPhotos(@Query("client_id") String clientId,
                                               @Query("page") String page);
}
