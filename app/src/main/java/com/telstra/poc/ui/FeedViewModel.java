package com.telstra.poc.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.telstra.poc.service.RetrofitApiResponse;
import com.telstra.poc.ui.FeedRepo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FeedViewModel extends ViewModel {
    private com.telstra.poc.ui.FeedRepo feedRepo;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private final MutableLiveData<RetrofitApiResponse> apiResponseMutableLiveData = new MutableLiveData<>();

    public FeedViewModel(FeedRepo feedRepo) {
        this.feedRepo = feedRepo;
    }

    public LiveData<RetrofitApiResponse> feedApiResponse() {
        return apiResponseMutableLiveData;
    }

    public void callFeedApi() {

        mDisposable.add(feedRepo.fetchFeed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> apiResponseMutableLiveData.setValue(RetrofitApiResponse.onSuccess(result)),
                        throwable -> apiResponseMutableLiveData.setValue(RetrofitApiResponse.onError(throwable))
                ));

    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }
}
