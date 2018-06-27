package com.example.owner.unsplashclient;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.owner.unsplashclient.listeners.OnPhotoListItemClick;
import com.example.owner.unsplashclient.model.entity.UnsplashEntity;

import java.util.ArrayList;
import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {

    private List<UnsplashEntity> mCollection;
    private OnPhotoListItemClick mPhotoListItemClick;

    public PhotosAdapter(OnPhotoListItemClick listener) {
        mCollection = new ArrayList<>();
        mPhotoListItemClick = listener;
    }

    public PhotosAdapter(List<UnsplashEntity> mCollection, OnPhotoListItemClick listener) {
        this.mCollection = mCollection;
        mPhotoListItemClick = listener;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_photos, parent, false);
        return new PhotosViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        final UnsplashEntity model = mCollection.get(position);
        ViewCompat.setTransitionName(holder.getImageView(), model.id);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return mCollection.size();
    }

    public void addItems(List<UnsplashEntity> entityList) {
        mCollection.addAll(entityList);
        notifyDataSetChanged();
    }

    public void addItem(UnsplashEntity entity) {
        mCollection.add(entity);
        notifyDataSetChanged();
    }

    public OnPhotoListItemClick getPhotoListItemClick() {
        return mPhotoListItemClick;
    }

    public void setPhotoListItemClick(OnPhotoListItemClick photoListItemClick) {
        mPhotoListItemClick = photoListItemClick;
    }

    public class PhotosViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding mBinding;
        private final ImageView mImageView;

        public PhotosViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            mBinding = viewDataBinding;
            mImageView = mBinding.getRoot().findViewById(R.id.item_image);
        }

        public void bind(UnsplashEntity object) {
            mBinding.setVariable(com.example.owner.unsplashclient.BR.item, object);
            mBinding.setVariable(BR.user, object.user);
            mBinding.executePendingBindings();
            mBinding.getRoot().setOnClickListener(v -> mPhotoListItemClick.openPhotoDetails(object, mImageView));
        }

        public ImageView getImageView() {
            return mImageView;
        }
    }
}
