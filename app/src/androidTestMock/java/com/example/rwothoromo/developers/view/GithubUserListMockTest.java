package com.example.rwothoromo.developers.view;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.rwothoromo.developers.R;
import com.example.rwothoromo.developers.RestServiceTestHelper;
import com.example.rwothoromo.developers.util.EspressoIdlingResource;
import com.example.rwothoromo.developers.view.GithubUserProfile;
import com.example.rwothoromo.developers.view.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Mock MainActivity test.
 */
@RunWith(AndroidJUnit4.class)
public class GithubUserListMockTest {

    /**
     * Mock MainActivity test rule.
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule
            = new ActivityTestRule<MainActivity>(MainActivity.class, true, true) {

        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent(targetContext, MainActivity.class);
            return intent;
        }
    };

    private MockWebServer server;

    /**
     * Set up the server.
     *
     * @throws Exception if fails
     */
    @Before
    public void setUp() throws Exception {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());

        // Instantiate a MockWebServer.
        server = new MockWebServer();

        // Schedule some responses.
        String fileName = "mock_api_200_response.json";
        server.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(),
                        fileName)));

        // Start the server.
        server.start();

        // Ask the server for its URL
//        BASE_URL = server.url("/").toString();
    }

    /**
     * Check for Clickable RecyclerView items.
     *
     * @throws Exception
     */
    @Test
    public void testClickableRecyclerViewItem() {

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));

        Intents.init();

        // Scroll to an item at a position and click on it.
        final int mockPosition = 0;
        onView(withId(R.id.recyclerView)).perform(
                actionOnItemAtPosition(mockPosition, click()));

        intended(allOf(hasComponent(GithubUserProfile.class.getName())));

        Intents.release();
    }

    /**
     * Tear down tests.
     *
     * @throws Exception if fails
     */
    @After
    public void tearDown() throws Exception {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
        server.shutdown();
    }
}
