package com.example.owner.unsplashclient.model.dto;

import com.example.owner.unsplashclient.model.api.PhotoApiImpl;
import com.example.owner.unsplashclient.model.api.PhotoApiInterface;
import com.example.owner.unsplashclient.model.entity.UnsplashEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class UnsplashPhotoModelIml implements UnsplashPhotoModel {

    public static final String CLIENT_ID = "45b8fba6886bb9525fa7e799049e6065ece9d4e03dbc7552d971866a977a5912";

    PhotoApiInterface mPhotoApiInterface = PhotoApiImpl.getApiInterface();
    @Override
    public Observable<List<UnsplashEntity>> getPhotosStat(int page) {
        return mPhotoApiInterface.getPhotos(CLIENT_ID,  Integer.toString(page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
