package com.rwothoromo.developers.service

import com.rwothoromo.developers.model.GithubUsersResponse

import retrofit2.Call
import retrofit2.http.GET

/**
 * This interface holds the GitHub API endpoints.
 */
interface GithubAPI {

    /**
     * Endpoint to get all Kotlin developer on GitHub, located in Kampala.
     *
     * @return API response
     */
    @get:GET("/search/users?q=type:user+language:kotlin+location:kampala")
    val results: Call<GithubUsersResponse>

//    TODO: Implement filter/search by name, username, city, country, tech stack and add details in profile
}
