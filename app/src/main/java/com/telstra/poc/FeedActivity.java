package com.telstra.poc;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.telstra.poc.app.FeedApp;
import com.telstra.poc.ui.FeedViewModel;
import com.telstra.poc.ui.FeedFragment;
import com.telstra.poc.utils.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedActivity extends AppCompatActivity {
    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.feeds_toolbar)
    Toolbar toolbar;

    FeedViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_activity);

        ButterKnife.bind(this);
        ((FeedApp) getApplication()).getAppComponent().doInjection(this);

        setSupportActionBar(toolbar);

        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel.class);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FeedFragment.newInstance())
                    .commitNow();
        }
    }
}
