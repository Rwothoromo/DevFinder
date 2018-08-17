package com.example.rwothoromo.developers;

import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.rwothoromo.developers.view.GithubUserProfile;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.rwothoromo.developers.constants.Constants.EXTRA_SHARE_INTENT_TEXT;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;

/**
 * GithubUserProfile test.
 */
@RunWith(AndroidJUnit4.class)
public class GithubUserProfileTest {

    /**
     * GithubUserProfile test rule.
     */
    @Rule
    public ActivityTestRule<GithubUserProfile> mGithubUserProfileTestRule
            = new ActivityTestRule<>(GithubUserProfile.class);

    /**
     * Check that GithubUserProfile is visible.
     */
    @Test
    public void displaysGithubUserProfile() {
        onView(withId(R.id.developerProfile)).check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE)));
    }

    /**
     * Check that GithubUserProfile is displayed.
     */
    @Test
    public void displaysGithubUserProfileDetails() {
        // Check that the profile view displays
        onView(withId(R.id.developerProfile)).check(matches(isDisplayed()));

        // Check to make sure the fields are empty by default
        onView(withId(R.id.usernameTextView)).check(matches(withText("")));
        onView(withId(R.id.profileUrlTextView)).check(matches(withText("")));
    }

    /**
     * Click the Back icon on the Action bar.
     */
    @Test
    public void clickActionBarBackAction() {
        // Click on the Back icon.
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
    }

    /**
     * Click the Share icon on the Action bar.
     */
    @Test
    public void clickActionBarShareAction() {

        Intents.init();

        // Click on the Share icon.
        onView(withId(R.id.action_share)).perform(click());

        Intents.intended(allOf(hasAction(Intent.ACTION_CHOOSER), hasExtra(is(Intent.EXTRA_INTENT),
                allOf(hasAction(Intent.ACTION_SEND),
                        hasExtra(Intent.EXTRA_TEXT, EXTRA_SHARE_INTENT_TEXT)))));
        Intents.release();
    }

}
