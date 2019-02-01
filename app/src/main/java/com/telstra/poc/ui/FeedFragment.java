package com.telstra.poc.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.telstra.poc.FeedActivity;
import com.telstra.poc.R;
import com.telstra.poc.models.FeedsModel;
import com.telstra.poc.service.RetrofitApiResponse;
import com.telstra.poc.ui.FeedsRecyclerViewAdapter;
import com.telstra.poc.utils.ViewModelFactory;
import com.telstra.poc.ui.*;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedFragment extends Fragment {
    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout mRefreshFeed;

    @BindView(R.id.feeds_recv)
    RecyclerView mFeeds_recv;

    com.telstra.poc.ui.FeedViewModel mViewModel;

    com.telstra.poc.ui.FeedsRecyclerViewAdapter mFeedsRecyclerViewAdaptar;

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.feed_fragment, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRefreshFeed.setOnRefreshListener(() -> mViewModel.callFeedApi());

        mFeeds_recv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFeeds_recv.setItemAnimator(new DefaultItemAnimator());

        mViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(com.telstra.poc.ui.FeedViewModel.class);

        mViewModel.feedApiResponse().observe(getActivity(), this::feedResponse);
        mViewModel.callFeedApi();
    }



    private void onSuccessFeedResponse(JsonElement response) {
        if (!response.isJsonNull()) {
            Log.i("***Response*** : ", response.toString());
            Gson mGson = new Gson();
            FeedsModel mFeedsModel = mGson.fromJson(response, FeedsModel.class);
            ((FeedActivity) getActivity()).getSupportActionBar().setTitle(mFeedsModel.getTitle());

            if (mFeedsRecyclerViewAdaptar == null)
            {
                mFeedsRecyclerViewAdaptar = new FeedsRecyclerViewAdapter(getContext(), getFragmentManager(), mFeedsModel.getRows());
                mFeeds_recv.setAdapter(mFeedsRecyclerViewAdaptar);
            }
            else
                {
                    mRefreshFeed.setRefreshing(false);
                    mFeedsRecyclerViewAdaptar.addAll(mFeedsModel.getRows());
                }
            ((FeedActivity) getActivity()).getSupportActionBar().setSubtitle(mFeedsRecyclerViewAdaptar.getItemCount() + " items");
        }
        else
            {
                Toast.makeText(getActivity(), getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
            }
    }

    private void feedResponse(RetrofitApiResponse retrofitApiResponse) {
        switch (retrofitApiResponse.apiCallStatus) {

            case ONSUCCESS:
                onSuccessFeedResponse(retrofitApiResponse.response);
                break;

            case ONERROR:
                Toast.makeText(getActivity(), getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}
