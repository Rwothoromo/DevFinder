package com.example.rwothoromo.developers.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This class gets the GitHub API text response and parses it to JSON.
 */
public class GithubUsersResponse {

    @SerializedName("items")
    private List<GithubUser> githubUsers;

    /**
     * Returns GithubUsersResponse in JSON format.
     *
     * @param response the API response
     * @return GithubUsersResponse the GSON reponse
     */
    public static GithubUsersResponse parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(response, GithubUsersResponse.class);
    }

    /**
     * Returns the GitHub user list.
     *
     * @return githubUsers List
     */
    public List<GithubUser> getResult() {
        return githubUsers;
    }
}
