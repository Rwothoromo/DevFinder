package com.rwothoromo.developers.models

import com.google.gson.annotations.SerializedName


data class GithubUsersResponse(
    @SerializedName("total_count") val count: Int = 0,
    @SerializedName("incomplete_results") val isIncompleteResults: Boolean = true,
    @SerializedName("items") val githubUserList: List<GithubUser> = ArrayList()
)
