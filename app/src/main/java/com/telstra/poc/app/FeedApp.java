package com.telstra.poc.app;

import android.app.Application;
import android.content.Context;

import com.telstra.poc.module.AppFeedComponent;
import com.telstra.poc.module.AppFeedModule;
import com.telstra.poc.module.DaggerAppFeedComponent;
import com.telstra.poc.module.UtilsFeedModule;

public class FeedApp extends Application {
    AppFeedComponent appFeedComponent;
    Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        appFeedComponent = DaggerAppFeedComponent.builder()
                .appFeedModule(new AppFeedModule(this))
                .utilsFeedModule(new UtilsFeedModule())
                .build();
    }

    public AppFeedComponent getAppComponent() {
        return appFeedComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
