package com.rwothoromo.developers.service

import com.rwothoromo.developers.model.GithubUsersResponse

import retrofit2.Call
import retrofit2.http.GET

/**
 * This interface holds the GitHub API endpoints.
 */
interface GithubAPI {

    /**
     * Endpoint to get all Java developer on GitHub, located in Nairobi.
     *
     * @return API response
     */
    @get:GET("/search/users?q=type:user+language:java+location:nairobi")
    val results: Call<GithubUsersResponse>

}
