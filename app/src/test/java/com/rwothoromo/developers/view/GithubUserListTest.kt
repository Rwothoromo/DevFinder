package com.rwothoromo.developers.view

import com.rwothoromo.developers.api.GithubService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class GithubUserListTest {

    private val mockResponseFilePath = "./src/main/res/raw/mock_api_200_response.json"
    private lateinit var mockResponseString: String

    private lateinit var githubService: GithubService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply(MockWebServer::start)

        githubService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)

        mockResponseString = File(mockResponseFilePath).readText(Charsets.UTF_8)
    }

    @Test
    fun checkGithubUsersResponseConvertsCorrectly() {
        runTest {
            mockWebServer.enqueue(MockResponse().setBody(mockResponseString))

            var githubUserFilter = "type:user location:kampala language:kotlin"
            val githubUsersResponse = githubService.getGithubUsersByLocation(githubUserFilter)
            val firstUser = githubUsersResponse.githubUserList.getOrNull(0)

            assertFalse(githubUsersResponse.isIncompleteResults)
            assertTrue(githubUsersResponse.count >= 0)
            assertEquals(2, githubUsersResponse.count) // (expected, actual)
            assertEquals(6739804, firstUser?.id)
            assertEquals("TheDancerCodes", firstUser?.username)
            assertEquals("https://api.github.com/users/TheDancerCodes", firstUser?.url)
            assertEquals("https://github.com/TheDancerCodes", firstUser?.htmlUrl)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
