package com.example.rwothoromo.developers.view

import com.example.rwothoromo.developers.model.GithubUser

/**
 * This class represents the GitHub User view interface.
 */
interface GithubUserView {

    /**
     * Method to act on available GitHub user list.
     *
     * @param githubUsers the GitHub user list
     */
    fun githubUsersReady(githubUsers: List<GithubUser>)

    /**
     * Method to act on failed GitHub API call.
     */
    fun failedDataRetrieval()
}
