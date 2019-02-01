package com.telstra.poc;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class FeedActivityTest {

    @Rule
    public ActivityTestRule<FeedActivity> mActivityTestRule = new ActivityTestRule<>(FeedActivity.class);
    private FeedActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        //called before executing test case
        mActivity = mActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        //called after executing test case
        mActivity = null;
    }
    @Test
    public void testLaunch() throws Exception{
        View view = mActivity.findViewById(R.id.toplayout);
        assertNotNull(view);
    }
    @Test
    public void testToolBar() throws Exception {
        View view  = mActivity.findViewById(R.id.feeds_toolbar);
        assertNotNull(view);

    }
    @Test
    public void testContainer() throws Exception {
        View view  = mActivity.findViewById(R.id.container);
        assertNotNull(view);

    }
}