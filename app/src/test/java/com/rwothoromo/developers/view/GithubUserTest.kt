package com.rwothoromo.developers.view

import com.rwothoromo.developers.api.GithubApiClient
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GithubUserTest {

    private var userType: String = "user"
    private var techStack: String = "Kotlin"
    private var city: String = "Kampala"

    @Test
    fun `GET Github Users check status`() {
        runBlocking {
            val response = GithubApiClient.githubService.getGithubUsersByLocation(
                githubUserFilter = "type:${userType.lowercase()} language:${techStack.lowercase()} location:${city.lowercase()}"
            )
            assertFalse(response.isIncompleteResults)
            assertTrue(response.count >= 0)
        }
    }

}
