package com.example.owner.unsplashclient;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.owner.unsplashclient.databinding.ActivityMainBinding;
import com.example.owner.unsplashclient.model.entity.UnsplashEntity;

import static com.example.owner.unsplashclient.PhotoListFragment.PHOTO_LIST_FRAGMENT;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_UNSPLASH_ENTITY = "TAG_UNSPLASH_ENTITY";
    public static final String EXTRA_IMAGE_TRANSITION_NAME = "EXTRA_IMAGE_TRANSITION_NAME";

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        startPhotoListFragment();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    public void startPhotoListFragment() {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        Fragment photoListFragment = mFragmentManager.findFragmentByTag(PHOTO_LIST_FRAGMENT);

        if (photoListFragment == null) {
            photoListFragment = new PhotoListFragment();
            mFragmentManager.beginTransaction()
                    .add(mBinding.actionContainer.getId(), photoListFragment, PHOTO_LIST_FRAGMENT)
                    .commit();
        } else {
            mFragmentManager.beginTransaction().show(photoListFragment).commit();
        }
    }

    public void startPhotoDetailsActivity(UnsplashEntity entity, ImageView sharedImageView) {
        Intent intent = new Intent(this, PhotoDetailsActivity.class);
        intent.putExtra(TAG_UNSPLASH_ENTITY, entity);
        intent.putExtra(EXTRA_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sharedImageView,
                ViewCompat.getTransitionName(sharedImageView));

        startActivity(intent);
    }
}
