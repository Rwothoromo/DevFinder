package com.rwothoromo.developers.view

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.support.test.InstrumentationRegistry.getContext
import android.support.test.InstrumentationRegistry.getInstrumentation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.rwothoromo.developers.RestServiceTestHelper
import com.rwothoromo.developers.util.EspressoIdlingResource
import com.rwothoromo.devfinder.R
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.InputStream


/**
 * Mock MainActivity test.
 */
@RunWith(AndroidJUnit4::class)
class GithubUserListMockInstrumentationTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    /**
     * Mock MainActivity test rule.
     */
    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<MainActivity> =
        object : ActivityTestRule<MainActivity>(MainActivity::class.java, true, true) {

            override fun getActivityIntent(): Intent {
                return Intent(context, MainActivity::class.java)
            }
        }

    private var server: MockWebServer? = null

    /**
     * Set up the server.
     *
     * @throws Exception if fails
     */
    @Before
    @Throws(Exception::class)
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)

        // Instantiate a MockWebServer.
        server = MockWebServer()

        // Schedule some responses.
        val fileName = R.raw.mock_api_200_response
        server!!.enqueue(
            MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setResponseCode(200)
                .setBody(
                    RestServiceTestHelper.getStringFromFile(
                        context,
                        fileName
                    )
                )
        )

        // Start the server.
        server!!.start()

        // Ask the server for its URL
        //        BASE_URL = server.url("/").toString();
    }

    /**
     * Check for Clickable RecyclerView items.
     *
     * @throws Exception
     */
    @Test
    fun testClickableRecyclerViewItem() {

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        Intents.init()

        // Scroll to an item at a position and click on it.
        val mockPosition = 0
        onView(withId(R.id.recyclerView)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(mockPosition, click())
        )

        intended(allOf<Intent>(hasComponent(GithubUserProfile::class.java.name)))

        Intents.release()
    }

    /**
     * Tear down tests.
     *
     * @throws Exception if fails
     */
    @After
    @Throws(Exception::class)
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
        server!!.shutdown()
    }
}
