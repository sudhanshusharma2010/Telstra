package com.telstra.poc.ui;

import com.google.gson.JsonElement;
import com.telstra.poc.service.IRetrofitApi;

import io.reactivex.Observable;

public class FeedRepo {

    private IRetrofitApi retrofitApi;

    public FeedRepo(IRetrofitApi retrofitApi) {
        this.retrofitApi = retrofitApi;
    }

    //Calling Feed Api
    public Observable<JsonElement> fetchFeed() {
        return retrofitApi.feed();
    }

}
