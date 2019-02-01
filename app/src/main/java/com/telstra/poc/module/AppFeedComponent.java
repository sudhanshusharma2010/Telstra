package com.telstra.poc.module;

import com.telstra.poc.FeedActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppFeedModule.class, UtilsFeedModule.class})
@Singleton
public interface AppFeedComponent {
    void doInjection(FeedActivity feedActivity);

    /*@Component.Builder
    interface Builder {
        AppFeedComponent build();
        Builder appModule(AppFeedModule appModule);
    }*/
}
