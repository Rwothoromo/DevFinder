package com.example.rwothoromo.developers.view

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.rwothoromo.developers.R
import com.example.rwothoromo.developers.util.EspressoIdlingResource
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * MainActivity test.
 */
@RunWith(AndroidJUnit4::class)
class GithubUserListInstrumentationTest {

    /**
     * MainActivity test rule.
     * Used for functional testing of a single activity.
     * The activity will be launched before each test annotated with @Test and before methods
     * annotated with @Before. It will be terminated after the test and methods annotated
     * with @After are complete. This rule allows direct access to the activity during the test.
     */
    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<MainActivity> = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {

        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getTargetContext()
            return Intent(targetContext, MainActivity::class.java)
        }
    }

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
        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE)))
    }

    /**
     * Displays the Toolbar.
     */
    @Test
    fun displaysToolbar() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    /**
     * View the MainActivity title.
     */
    @Test
    fun displaysToolbarTitle() {
        onView(withText(R.string.app_name)).check(matches(withParent(withId(R.id.toolbar))))
    }

    /**
     * View the MainActivity subtitle.
     */
    @Test
    fun displaysToolbarSubtitle() {
        onView(withText(R.string.app_subtitle)).check(matches(withParent(withId(R.id.toolbar))))
    }

    /**
     * Click the Search icon on the Action bar.
     */
    @Test
    fun clickActionBarSearchItem() {
        // Click on the Search icon.
        onView(withId(R.id.action_search)).perform(click())
    }

    /**
     * Click Settings in the menu items.
     */
    @Test
    fun clickActionBarOverflowSettings() {
        // Open the options menu OR open the overflow menu, depending on whether
        // the device has a hardware or software overflow menu button.
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)

        // Click the item.
        onView(withText(R.string.action_settings)).perform(click())
    }

    /**
     * Click Refresh in the menu items.
     */
    @Test
    fun clickActionBarOverflowRefresh() {
        // Open the options menu OR open the overflow menu, depending on whether
        // the device has a hardware or software overflow menu button.
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)

        // Click the item.
        onView(withText(R.string.action_refresh)).perform(click())
    }

    /**
     * Scroll to a GitHub user on the RecyclerView and click one.
     */
    @Test
    fun clickableRecyclerViewItems() {

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        Intents.init()

        // Scroll to an item at a position and click on it.
        val mockPosition = 0
        onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(mockPosition, click()))

        intended(allOf<Intent>(hasComponent(GithubUserProfile::class.java.name)))

        Intents.release()
    }

    /**
     * Unregister the resource.
     */
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
}
