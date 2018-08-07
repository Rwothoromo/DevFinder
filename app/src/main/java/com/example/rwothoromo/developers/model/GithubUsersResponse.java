package com.example.rwothoromo.developers.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubUsersResponse {

	@SerializedName("items")
	private List<GithubUser> githubUsers;

	public static GithubUsersResponse parseJSON(String response) {
		Gson gson = new GsonBuilder().create();
		GithubUsersResponse githubUsersResponse = gson.fromJson(response, GithubUsersResponse.class);
		return githubUsersResponse;
	}
}
