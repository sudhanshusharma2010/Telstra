package com.telstra.poc.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppFeedModule {
    private Context mContext;

    public AppFeedModule(Context context) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    Context getFeedContext() {
        return mContext;
    }
}
