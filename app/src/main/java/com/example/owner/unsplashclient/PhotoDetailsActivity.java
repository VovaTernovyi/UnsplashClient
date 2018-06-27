package com.example.owner.unsplashclient;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.owner.unsplashclient.databinding.ActivityPhotoDetailsBinding;
import com.example.owner.unsplashclient.model.entity.UnsplashEntity;

import static com.example.owner.unsplashclient.MainActivity.TAG_UNSPLASH_ENTITY;
import static com.example.owner.unsplashclient.PhotoDetailFragment.PHOTO_DETAILS_FRAGMENT;

public class PhotoDetailsActivity extends AppCompatActivity {

    public static final String TRANSITION_NAME = "TRANSITION_NAME";

    ActivityPhotoDetailsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_details);
        supportPostponeEnterTransition();

        Bundle bundle = getIntent().getExtras();
        UnsplashEntity entity = bundle != null ? (UnsplashEntity) bundle.getSerializable(TAG_UNSPLASH_ENTITY) : null;

//        String imageTransitionName = bundle.getString(EXTRA_IMAGE_TRANSITION_NAME);

        startPhotoDetailsFragment(entity);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void startPhotoDetailsFragment(UnsplashEntity entity) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        Fragment photoDetailsFragment = mFragmentManager.findFragmentByTag(PHOTO_DETAILS_FRAGMENT);

        if (photoDetailsFragment == null) {
            photoDetailsFragment = new PhotoDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(TAG_UNSPLASH_ENTITY, entity);
//            bundle.putString(TRANSITION_NAME, imageTransitionName);
            photoDetailsFragment.setArguments(bundle);
            mFragmentManager.beginTransaction()
                    .add(mBinding.getRoot().findViewById(R.id.details_container).getId(), photoDetailsFragment, PHOTO_DETAILS_FRAGMENT)
                    .commit();
        } else {
            mFragmentManager.beginTransaction().show(photoDetailsFragment);
        }
    }

//      .setCustomAnimations(R.animator.fragment_slide_left_enter,
//                           R.animator.fragment_slide_left_exit,
//                           R.animator.fragment_slide_right_enter,
//                           R.animator.fragment_slide_right_exit)
}
