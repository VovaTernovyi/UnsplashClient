package com.example.owner.unsplashclient.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.owner.unsplashclient.model.dto.UnsplashPhotoModel;
import com.example.owner.unsplashclient.model.dto.UnsplashPhotoModelIml;
import com.example.owner.unsplashclient.model.entity.UnsplashEntity;
import com.example.owner.unsplashclient.view_model.interfaces.IUnsplashViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UnsplashViewModel extends ViewModel implements IUnsplashViewModel {

    private UnsplashPhotoModel mModel;
    private MutableLiveData<List<UnsplashEntity>> mCollectionLiveData = new MutableLiveData<>();

    public UnsplashViewModel() {
        mModel = new UnsplashPhotoModelIml();
    }

    @Override
    public void onLoadData(int page) {
        mModel.getPhotosStat(page).subscribe(new Observer<List<UnsplashEntity>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<UnsplashEntity> unsplashEntities) {

                mCollectionLiveData.setValue(unsplashEntities);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public MutableLiveData<List<UnsplashEntity>> getCollection() {
        return mCollectionLiveData;
    }

    @Override
    public void onStop() {

    }
}
