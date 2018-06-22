package com.example.owner.unsplashclient;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.example.owner.unsplashclient.databinding.ActivityMainBinding;
import com.example.owner.unsplashclient.model.dto.UnsplashPhotoModel;
import com.example.owner.unsplashclient.model.entity.UnsplashEntity;
import com.example.owner.unsplashclient.view_model.UnsplashViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyView {

    private UnsplashViewModel mViewModel;
    private List<UnsplashEntity> mCollection = new ArrayList<>();

    ActivityMainBinding mBinding;

    private RecyclerView mRecyclerView;
    private PhotosAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = new UnsplashViewModel(this);
        mViewModel.onLoadData(1);
        mRecyclerView = mBinding.recyclerView;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        super.onCreateView(parent, name, context, attrs);

        DataBindingUtil.getBinding(getCurrentFocus());
        mAdapter = new PhotosAdapter();
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return null;
    }

    @Override
    public void showData(List<UnsplashEntity> entityList) {
        mAdapter.addItems(entityList);
        Toast.makeText(this, "Request Success! Count: " + mCollection.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Request error message: " + error, Toast.LENGTH_LONG).show();
    }
}
