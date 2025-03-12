package com.rwothoromo.developers.presenter

import com.rwothoromo.developers.model.GithubUsersResponse
import com.rwothoromo.developers.service.GithubService
import com.rwothoromo.developers.util.EspressoIdlingResource
import com.rwothoromo.developers.view.GithubUserView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This class is a service that leverages the GithubApi interface.
 * It represents a view of the data retrieved.
 */
class GithubPresenter
/**
 * Constructor for GithubPresenter class.
 *
 * @param githubUserView the GithubUserView
 */
(private val githubUserView: GithubUserView) {
    private var githubService: GithubService? = null

    init {

        if (this.githubService == null) {
            this.githubService = GithubService()
        }
    }

    /**
     * Get GitHub users from the GitHub API.
     */
    fun getGithubUsers() {
        EspressoIdlingResource.increment()
        githubService!!.api.results
                .enqueue(object : Callback<GithubUsersResponse> {
                    override fun onResponse(call: Call<GithubUsersResponse>,
                                            response: Response<GithubUsersResponse>) {

                        val githubUsersResponse = response.body()

                        if (githubUsersResponse != null && githubUsersResponse.result != null) {
                            val githubUsers = githubUsersResponse.result
                            githubUserView.githubUsersReady(githubUsers)
                        }
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<GithubUsersResponse>, t: Throwable) {
                        githubUserView.failedDataRetrieval()
                        EspressoIdlingResource.decrement()
                    }
                })
    }
}
