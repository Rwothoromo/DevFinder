package com.example.rwothoromo.developers.service;

import com.example.rwothoromo.developers.model.Data;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * This interface holds the GitHub API endpoints
 */
public interface GithubAPI {

	@GET("/search/users?q=type:user+language:java+location:nairobi")
	Call<Data> getResults();

}
