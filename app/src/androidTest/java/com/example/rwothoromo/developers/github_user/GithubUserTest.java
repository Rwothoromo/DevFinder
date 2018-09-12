package com.example.rwothoromo.developers.github_user;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import com.example.rwothoromo.developers.model.GithubUser;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests that the GithubUser parcelable interface is implemented correctly.
 */
@RunWith(AndroidJUnit4.class)
public class GithubUserTest {

    /**
     * Test GitHub user parcel read and write.
     */
    @Test
    public void githubUserParcelableWriteRead() {
        // Set up the Parcelable object to send and receive.
        GithubUser githubUser = new GithubUser(
                1,
                "Username",
                "https://avatars0.githubusercontent.com/u/6739804?v=4",
                "https://github.com/Username"
        );

        // Write the data.
        Parcel parcel = Parcel.obtain();
        githubUser.writeToParcel(parcel, githubUser.describeContents());

        // Reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        GithubUser createdFromParcel = GithubUser.CREATOR.createFromParcel(parcel);

        // Verify that the received data is correct.
        assertThat(createdFromParcel.getId(), is(1));
        assertThat(createdFromParcel.getUsername(), is("Username"));
        assertThat(createdFromParcel.getAvatarUrl(), is("https://avatars0.githubusercontent.com/u/6739804?v=4"));
        assertThat(createdFromParcel.getHtmlUrl(), is("https://github.com/Username"));
    }
}
