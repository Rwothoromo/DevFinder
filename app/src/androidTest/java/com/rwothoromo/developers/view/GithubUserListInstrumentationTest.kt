package com.rwothoromo.developers.view

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressMenuKey
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rwothoromo.developers.constants.Constants.DIALOG_DELAY_TIME
import com.rwothoromo.developers.util.DelayIdlingResource
import com.rwothoromo.developers.util.EspressoIdlingResource
import com.rwothoromo.devfinder.R
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * MainActivity test.
 */
@RunWith(AndroidJUnit4::class)
class GithubUserListInstrumentationTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    val intent = Intent(context, MainActivity::class.java)

    /**
     * Very important for launching activity and passing tests
     */
    lateinit var activityScenario: ActivityScenario<MainActivity>

    private val delayIdlingResource = DelayIdlingResource(DIALOG_DELAY_TIME)

    /**
     * Register any resource that needs to be synchronized with Espresso before the test is run.
     */
    @Before
    fun setUp() {
        // Register idling resource before any UI operations
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(delayIdlingResource)

        activityScenario = launchActivity<MainActivity>(intent)
    }

    /**
     * Click the Search icon on the Action bar.
     */
    @Test
    fun clickActionBarSearchItem() {
        runBlocking {
            delayIdlingResource.delay {
                onView(withId(R.id.action_search)).check(matches(isDisplayed())).perform(click())
            }
        }
    }

    /**
     * Click Settings in the menu items.
     */
    @Test
    fun clickActionBarOverflowSettings() {
        delayIdlingResource.delay {
            onView(isRoot()).perform(pressMenuKey())
            onView(withText(R.string.action_settings)).check(matches(isDisplayed()))
            onView(withText(R.string.action_settings)).perform(click())
        }
    }

    /**
     * Click Refresh in the menu items.
     */
    @Test
    fun clickActionBarOverflowRefresh() {
        delayIdlingResource.delay {
            onView(isRoot()).perform(pressMenuKey())
            onView(withText(R.string.action_refresh)).check(matches(isDisplayed())).perform(click())
        }
    }


    /**
     * Scroll to a GitHub user on the RecyclerView and click one.
     */
    @Test
    fun clickableRecyclerViewItems() {
        delayIdlingResource.delay {
            // Verify toolbar visibility
            onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
            onView(withText(R.string.app_name)).check(matches(withParent(withId(R.id.toolbar))))

            // Verify RecyclerView visibility
            onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

            // Scroll to and click GithubUser item
            onView(withId(R.id.recyclerView))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

            // Verify intent
            Intents.init()
            intended(hasComponent(GithubUserProfile::class.java.name))
            Intents.release()
        }
    }

    /**
     * Unregister the resource.
     */
    @After
    fun tearDown() {
        activityScenario.close()

        // Clean up resources
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(delayIdlingResource)
    }
}
