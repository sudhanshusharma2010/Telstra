package com.telstra.poc.service;

import com.google.gson.JsonElement;
import com.telstra.poc.utils.WebUrls;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IRetrofitApi {
    @GET(WebUrls.FEED_URL)
    Observable<JsonElement> feed();
}
