package com.telstra.poc;

import android.content.Context;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final String TAG = "ExampleUnitTest";

    //As we don't have access to Context in our JUnit test classes, we need to mock it
    @Mock
    Context mMockContext;
    @Test
    public void readStringFromContext() {
        /*//Returns the TEST_STRING when getString(R.string.hello_world) is called
        when(mMockContext.getString(R.string.text_hello_word)).thenReturn(TEST_STRING);
        //Creates an object of the ClassUnderTest with the mock context
        FeedActivity objectUnderTest = new FeedActivity(mMockContext);
        //Stores the return value of getHelloWorldString() in result
        String result = objectUnderTest.getHelloWorldString();
        //Asserts that result is the value of TEST_STRING
        assertThat(result, is(TEST_STRING))*/;
    }
}