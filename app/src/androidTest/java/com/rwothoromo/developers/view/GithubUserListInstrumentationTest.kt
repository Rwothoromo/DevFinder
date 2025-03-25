package com.rwothoromo.developers.view

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rwothoromo.developers.constants.Constants.DIALOG_DELAY_TIME
import com.rwothoromo.developers.util.EspressoIdlingResource
import com.rwothoromo.developers.util.TestUtils
import com.rwothoromo.devfinder.R
import org.hamcrest.CoreMatchers.allOf
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

    private lateinit var intent: Intent

    /**
     * Very important for launching activity and passing tests
     */
    lateinit var activityScenario: ActivityScenario<MainActivity>

    /**
     * Register any resource that needs to be synchronized with Espresso before the test is run.
     */
    @Before
    fun setUp() {
        intent = Intent(context, MainActivity::class.java)
        activityScenario = launchActivity<MainActivity>(intent)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        // Using TestUtils.executeWithDelay below to skip the alertDialog in MainActivity
    }

    /**
     * Click the Search icon on the Action bar.
     */
    @Test
    fun clickActionBarSearchItem() {
        // Wait 6 seconds for alertDialog to close
        TestUtils.executeWithDelay(6000L) {
            Thread.sleep(2000L)
            // Open the options menu OR open the overflow menu, depending on whether
            // the device has a hardware or software overflow menu button.
            openActionBarOverflowOrOptionsMenu(context)

            // Click on the Search icon.
            onView(withId(R.id.action_search)).perform(click())
        }
    }

    /**
     * Click Settings in the menu items.
     */
    @Test
    fun clickActionBarOverflowSettings() {
        // Wait 7 seconds for alertDialog to close
        TestUtils.executeWithDelay(7000L) {
            // Open the options menu OR open the overflow menu, depending on whether
            // the device has a hardware or software overflow menu button.
            openActionBarOverflowOrOptionsMenu(context)

            // Click the item.
            onView(withText(R.string.action_settings)).perform(click())
        }
    }

    /**
     * Click Refresh in the menu items.
     */
    @Test
    fun clickActionBarOverflowRefresh() {
        // Wait 8 seconds for alertDialog to close
        TestUtils.executeWithDelay(8000L) {
            // Open the options menu OR open the overflow menu, depending on whether
            // the device has a hardware or software overflow menu button.
            openActionBarOverflowOrOptionsMenu(context)

            // Click the item.
            onView(withText(R.string.action_refresh)).perform(click())
        }
    }

    /**
     * Scroll to a GitHub user on the RecyclerView and click one.
     */
    @Test
    fun clickableRecyclerViewItems() {
        // Wait 9 seconds for data to load and alertDialog to close
        TestUtils.executeWithDelay(9000L) {
            // Confirm toolbar is displayed
            onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
            onView(withText(R.string.app_name)).check(matches(withParent(withId(R.id.toolbar))))
            onView(withText(R.string.app_name)).check(matches(withParent(withId(R.id.toolbar))))

            // Confirm recyclerview is displayed
            onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
            onView(withId(R.id.recyclerView)).check(
                matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))
            )

            Intents.init()

            // Scroll to an item at a position and click on it.
            val mockPosition = 0
            onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    mockPosition,
                    click()
                )
            )

            intended(allOf<Intent>(hasComponent(GithubUserProfile::class.java.name)))

            Intents.release()
        }
    }

    /**
     * Unregister the resource.
     */
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
        activityScenario.close()
    }
}
