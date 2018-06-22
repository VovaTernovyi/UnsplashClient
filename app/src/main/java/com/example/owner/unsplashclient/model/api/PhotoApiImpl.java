package com.example.owner.unsplashclient.model.api;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoApiImpl {

    public static final String BASE_URL = "https://api.unsplash.com";

    public static PhotoApiInterface getApiInterface() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PhotoApiInterface.class);

    }
}
