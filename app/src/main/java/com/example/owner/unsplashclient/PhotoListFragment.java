package com.example.owner.unsplashclient;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.owner.unsplashclient.databinding.FragmentPhotoListBinding;
import com.example.owner.unsplashclient.listeners.OnPhotoListItemClick;
import com.example.owner.unsplashclient.model.entity.UnsplashEntity;
import com.example.owner.unsplashclient.view_model.UnsplashViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoListFragment extends Fragment implements MyView {

    public static final String PHOTO_LIST_FRAGMENT = "PHOTO_LIST_FRAGMENT";
    public static final int PAGE_SIZE = 10;

    FragmentPhotoListBinding mBinding;

    private UnsplashViewModel mViewModel;
    private List<UnsplashEntity> mCollection = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private PhotosAdapter mAdapter;
    private StaggeredGridLayoutManager mLayoutManager;

    private boolean isLastPage = false;
    private int mCurrentPage = 1;
    private boolean isLoading = false;

    public PhotoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo_list, container, false);

        mRecyclerView = mBinding.recyclerView;
        mRecyclerView.setHasFixedSize(true);

        OnPhotoListItemClick onPhotoListItemClick = (UnsplashEntity entity) -> {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).startPhotoDetailsFragment(entity);
                }
        };

        mAdapter = new PhotosAdapter(onPhotoListItemClick);
        mLayoutManager = new StaggeredGridLayoutManager(2,1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int[] aaa = null;
                int[] firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPositions(aaa);

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition[0]) >= totalItemCount
                            && firstVisibleItemPosition[0] >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        ++mCurrentPage;
                        mViewModel.onLoadData(mCurrentPage);
                    }
                }
            }
        };

        mRecyclerView.addOnScrollListener(onScrollListener);

        mViewModel = new UnsplashViewModel(this);
        mViewModel.onLoadData(mCurrentPage);

        return mBinding.getRoot();
    }

    @Override
    public void showData(List<UnsplashEntity> entityList) {
        mAdapter.addItems(entityList);
        Toast.makeText(getContext(), "Request Success! Count: " + mCollection.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), "Request error message: " + error, Toast.LENGTH_LONG).show();
    }
}
