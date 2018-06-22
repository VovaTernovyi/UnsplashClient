package com.example.owner.unsplashclient.view_model;

import android.arch.lifecycle.ViewModel;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.owner.unsplashclient.MyView;
import com.example.owner.unsplashclient.PhotosAdapter;
import com.example.owner.unsplashclient.model.dto.UnsplashPhotoModel;
import com.example.owner.unsplashclient.model.dto.UnsplashPhotoModelIml;
import com.example.owner.unsplashclient.model.entity.UnsplashEntity;
import com.example.owner.unsplashclient.view_model.interfaces.IUnsplashViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UnsplashViewModel extends ViewModel implements IUnsplashViewModel {

    private UnsplashPhotoModel mModel;
    private MyView mView;

    public UnsplashViewModel(MyView view) {
        mModel = new UnsplashPhotoModelIml();
        mView = view;
    }

    @Override
    public void onLoadData(int page) {
        mModel.getPhotosStat(page).subscribe(new Observer<List<UnsplashEntity>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<UnsplashEntity> unsplashEntities) {
                mView.showData(unsplashEntities);
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onStop() {

    }
}
