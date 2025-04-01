package com.rwothoromo.developers.view

import android.os.Parcel
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rwothoromo.developers.models.GithubUser
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests that the GithubUser parcelable interface is implemented correctly.
 * For Parcelable and Parcel classes, use an Espresso test to run against the Android API.
 * If run as a JUnit test, we get a null exception when `Parcel.obtain()` returns null in the test.
 */
@RunWith(AndroidJUnit4::class)
class GithubUserInstrumentationTest {

    /**
     * Test GitHub user parcel read and write.
     */
    @Test
    fun githubUserParcelableWriteRead() {
        // Set up the Parcelable object to send and receive.
        val githubUser = GithubUser(
            1,
            "Username",
            "https://avatars0.githubusercontent.com/u/6739804?v=4",
            "https://github.com/Username"
        )

        // Write the data.
        val parcel = Parcel.obtain()
        githubUser.writeToParcel(parcel, githubUser.describeContents())

        // Reset the parcel for reading.
        parcel.setDataPosition(0)

        // Read the data.
        val createdFromParcel = GithubUser.CREATOR.createFromParcel(parcel)

        // Verify that the received data is correct.
        assertEquals(createdFromParcel.id, 1)
        assertEquals(createdFromParcel.username, "Username")
        assertEquals(
            createdFromParcel.avatarUrl,
            "https://avatars0.githubusercontent.com/u/6739804?v=4"
        )
        assertEquals(createdFromParcel.htmlUrl, "https://github.com/Username")
    }
}
