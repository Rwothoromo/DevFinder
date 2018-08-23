package com.example.rwothoromo.developers;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.rwothoromo.developers.util.EspressoIdlingResource;
import com.example.rwothoromo.developers.view.GithubUserProfile;
import com.example.rwothoromo.developers.view.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * MainActivity test.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    /**
     * MainActivity test rule.
     * Used for functional testing of a single activity.
     * The activity will be launched before each test annotated with @Test and before methods
     * annotated with @Before. It will be terminated after the test and methods annotated
     * with @After are complete. This rule allows direct access to the activity during the test.
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule
            = new ActivityTestRule<MainActivity>(MainActivity.class) {

        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent(targetContext, MainActivity.class);
            return intent;
        }
    };

    /**
     * Register any resource that needs to be synchronized with Espresso before the test is run.
     *
     * @throws Exception when method fails
     */
    @Before
    public void setUp() throws Exception {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    /**
     * Check that RecyclerView is displayed.
     */
    @Test
    public void displaysRecyclerView() {
        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE)));
    }

    /**
     * Displays the Toolbar.
     */
    @Test
    public void displaysToolbar() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    /**
     * View the MainActivity title.
     */
    @Test
    public void displaysToolbarTitle() {
        onView(withText(R.string.app_name)).check(matches(withParent(withId(R.id.toolbar))));
    }

    /**
     * View the MainActivity subtitle.
     */
    @Test
    public void displaysToolbarSubtitle() {
        onView(withText(R.string.app_subtitle)).check(matches(withParent(withId(R.id.toolbar))));
    }

    /**
     * Click the Search icon on the Action bar.
     */
    @Test
    public void clickActionBarSearchItem() {
        // Click on the Search icon.
        onView(withId(R.id.action_search)).perform(click());
    }

    /**
     * Click Settings in the menu items.
     */
    @Test
    public void clickActionBarOverflowSettings() {
        // Open the options menu OR open the overflow menu, depending on whether
        // the device has a hardware or software overflow menu button.
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        // Click the item.
        onView(withText(R.string.action_settings)).perform(click());
    }

    /**
     * Click Refresh in the menu items.
     */
    @Test
    public void clickActionBarOverflowRefresh() {
        // Open the options menu OR open the overflow menu, depending on whether
        // the device has a hardware or software overflow menu button.
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        // Click the item.
        onView(withText(R.string.action_refresh)).perform(click());
    }

    /**
     * Scroll to a GitHub user on the RecyclerView and click one.
     */
    @Test
    public void clickableRecyclerViewItems() {

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));

        Intents.init();

        // Scroll to an item at a position and click on it.
        final int mockPosition = 0;
        onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(mockPosition, click()));

        intended(allOf(hasComponent(GithubUserProfile.class.getName())));

        Intents.release();
    }

    /**
     * Unregister the resource.
     *
     * @throws Exception when method fails
     */
    @After
    public void tearDown() throws Exception {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }
}
