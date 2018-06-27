package com.example.owner.unsplashclient.listeners;

import android.widget.ImageView;

import com.example.owner.unsplashclient.model.entity.UnsplashEntity;

public interface OnPhotoListItemClick {

    void openPhotoDetails(UnsplashEntity entity, ImageView imageView);
}
