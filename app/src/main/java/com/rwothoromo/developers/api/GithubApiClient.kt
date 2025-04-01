package com.rwothoromo.developers.api


object GithubApiClient {
    val githubService: GithubService by lazy {
        RetrofitClient.retrofit.create(GithubService::class.java)
    }
}