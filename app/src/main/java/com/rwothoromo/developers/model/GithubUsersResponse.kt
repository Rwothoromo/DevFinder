package com.rwothoromo.developers.model

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName

/**
 * This class gets the GitHub API text response and parses it to JSON.
 */
class GithubUsersResponse {

    /**
     * Returns the GitHub user list.
     *
     * @return githubUsers List
     */
    @SerializedName("items")
    val result: List<GithubUser>? = null

    companion object {

        /**
         * Returns GithubUsersResponse in JSON format.
         *
         * @param response the API response
         * @return GithubUsersResponse the GSON reponse
         */
        fun parseJSON(response: String): GithubUsersResponse {
            val gson = GsonBuilder().create()
            return gson.fromJson(response, GithubUsersResponse::class.java)
        }
    }
}
