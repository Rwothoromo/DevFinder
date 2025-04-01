package com.rwothoromo.developers.api

import com.rwothoromo.developers.constants.Constants.BASE_GITHUB_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * This class is for the GitHub service and helps in sending out network requests to the GitHub API.
 * TODO: Set up HTTP requests for respective API endpoints.
 * For GitHub User list, use https://api.github.com/users or see https://docs.github.com/en/rest/users/users
 * For GitLab Projects list, use https://gitlab.com/api/v4/projects or see https://docs.gitlab.com/api/rest
 * TODO: GitlabApiClient just like [GithubApiClient]
 *
 * Insufferable Endpoints - Must Use GET requests with authorization headers specific to each service.
 * For GitLab, use https://gitlab.com/api/v4/users or see https://docs.gitlab.com/api/users (Requires Token)
 * For Bitbucket, use https://api.bitbucket.org/2.0/user or see https://developer.atlassian.com/cloud/bitbucket/rest/api-group-users/
 * Or https://developer.atlassian.com/cloud/bitbucket/rest/api-group-workspaces/#api-workspaces-workspace-members-get (Requires workspace permissions)
 */

object RetrofitClient {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_GITHUB_URL)
            .client(HttpClient.okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
