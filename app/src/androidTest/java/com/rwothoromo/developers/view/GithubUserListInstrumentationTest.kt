package com.rwothoromo.developers.view

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
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
import com.rwothoromo.developers.util.EspressoIdlingResource
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

    /**
     * Register any resource that needs to be synchronized with Espresso before the test is run.
     */
    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    /**
     * Check that RecyclerView is displayed.
     */
    @Test
    fun displaysRecyclerView() {
        launchActivity<MainActivity>().use {
            onView(withId(R.id.recyclerView)).check(
                matches(
                    withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                    )
                )
            )
        }
    }

    /**
     * Displays the Toolbar.
     */
    @Test
    fun displaysToolbar() {
        launchActivity<MainActivity>().use {
            onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
            onView(withText(R.string.app_name)).check(matches(withParent(withId(R.id.toolbar))))
        }
    }

    /**
     * Click the Search icon on the Action bar.
     */
    @Test
    fun clickActionBarSearchItem() {
        launchActivity<MainActivity>().use {
            // Click on the Search icon.
            onView(withId(R.id.action_search)).perform(click())
        }
    }

    /**
     * Click Settings in the menu items.
     */
    @Test
    fun clickActionBarOverflowSettings() {
        launchActivity<MainActivity>().use {
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
        launchActivity<MainActivity>().use {
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
        launchActivity<MainActivity>().use {
            onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

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
    }
}
