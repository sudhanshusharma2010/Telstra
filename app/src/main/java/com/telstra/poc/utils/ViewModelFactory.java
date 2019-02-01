package com.telstra.poc.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.telstra.poc.ui.FeedRepo;
import com.telstra.poc.ui.FeedViewModel;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private FeedRepo feedRepo;

    @Inject
    public ViewModelFactory(FeedRepo feedRepo) {
        this.feedRepo = feedRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FeedViewModel.class)) {
            return (T) new FeedViewModel(feedRepo);
        }
        throw new IllegalArgumentException("Unknown classname");
    }
}
