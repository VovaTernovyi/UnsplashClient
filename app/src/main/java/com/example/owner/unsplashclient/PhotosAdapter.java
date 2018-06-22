package com.example.owner.unsplashclient;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.owner.unsplashclient.model.entity.UnsplashEntity;

import java.util.ArrayList;
import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {

    private List<UnsplashEntity> mCollection;

    public PhotosAdapter() {
        mCollection = new ArrayList<>();
    }

    public PhotosAdapter(List<UnsplashEntity> mCollection) {
        this.mCollection = mCollection;
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

    public class PhotosViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding mBinding;

        public PhotosViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            mBinding = viewDataBinding;
        }

        public void bind(UnsplashEntity object) {
            mBinding.setVariable(com.example.owner.unsplashclient.BR.item, object);
            mBinding.executePendingBindings();
        }
    }
}
