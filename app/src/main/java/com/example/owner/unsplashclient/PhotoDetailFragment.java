package com.example.owner.unsplashclient;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owner.unsplashclient.databinding.FragmentPhotoDetailBinding;
import com.example.owner.unsplashclient.model.entity.UnsplashEntity;

import static com.example.owner.unsplashclient.MainActivity.TAG_UNSPLASH_ENTITY;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoDetailFragment extends Fragment {

    public static final String PHOTO_DETAILS_FRAGMENT = "PHOTO_DETAILS_FRAGMENT";

    FragmentPhotoDetailBinding mBinding;
    private UnsplashEntity mUnsplashEntity;
    private String mImageTransitionName;

    public PhotoDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (getArguments() != null) {
            if (getArguments().getSerializable(TAG_UNSPLASH_ENTITY) != null
                    && getArguments().getSerializable(TAG_UNSPLASH_ENTITY) instanceof UnsplashEntity) {
                mUnsplashEntity = (UnsplashEntity) getArguments().getSerializable(TAG_UNSPLASH_ENTITY);
//                mImageTransitionName = getArguments().getString(TRANSITION_NAME);
            }
        }

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo_detail, container, false);
        mBinding.setVariable(BR.item, mUnsplashEntity);
//        mBinding.photoImageView.setTransitionName(mImageTransitionName);
        return mBinding.getRoot();
    }


}
