package com.example.rwothoromo.developers.service;

import com.example.rwothoromo.developers.model.GithubUsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * This interface holds the GitHub API endpoints.
 */
public interface GithubAPI {

    /**
     * Endpoint to get all Java developer on GitHub, located in Nairobi.
     *
     * @return API response
     */
    @GET("/search/users?q=type:user+language:java+location:nairobi")
    Call<GithubUsersResponse> getResults();

}
