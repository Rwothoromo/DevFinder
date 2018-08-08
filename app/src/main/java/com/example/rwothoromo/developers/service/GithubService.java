package com.example.rwothoromo.developers.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is for the GitHub service
 * It helps in sending out network requests to the GitHub API
 */
public class GithubService {

	private Retrofit retrofit = null;

	/**
	 * Returns a new instance of the GithubAPI interface
	 *
	 * @return GithubAPI interface
	 */
	public GithubAPI getAPI() {
		String BASE_URL = "https://api.github.com";

		if (retrofit == null) {
			retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}

		return retrofit.create(GithubAPI.class);
	}
}
