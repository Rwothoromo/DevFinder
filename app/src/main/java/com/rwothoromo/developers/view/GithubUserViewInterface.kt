package com.rwothoromo.developers.view

import com.rwothoromo.developers.models.GithubUser
import com.rwothoromo.developers.models.GithubUsersResponse

/**
 * This class represents the GitHub User view interface.
 */
interface GithubUserViewInterface {

    /**
     * Method to act on available GitHub user list.
     *
     * @param githubUsersResponse that contains the GitHub user list
     */
    fun githubUsersReady(githubUsersResponse: GithubUsersResponse)

    /**
     * Method to act on failed GitHub API call.
     */
    fun failedDataRetrieval(errorMessage: String)
}
