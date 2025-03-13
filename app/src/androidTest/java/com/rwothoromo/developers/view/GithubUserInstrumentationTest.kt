package com.rwothoromo.developers.view

import android.os.Parcel
import androidx.test.runner.AndroidJUnit4
import com.rwothoromo.developers.model.GithubUser
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests that the GithubUser parcelable interface is implemented correctly.
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
        assertThat(createdFromParcel.id, `is`(1))
        assertThat(createdFromParcel.username, `is`("Username"))
        assertThat(
            createdFromParcel.avatarUrl,
            `is`("https://avatars0.githubusercontent.com/u/6739804?v=4")
        )
        assertThat(createdFromParcel.htmlUrl, `is`("https://github.com/Username"))
    }
}
