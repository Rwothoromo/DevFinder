package com.example.rwothoromo.developers.view;

import com.example.rwothoromo.developers.model.GithubUser;

import java.util.List;

/**
 * This class represents the GitHub User view interface.
 */
public interface GithubUserView {

    /**
     * Method to act on available GitHub user list.
     *
     * @param githubUsers the GitHub user list
     */
    void githubUsersReady(List<GithubUser> githubUsers);

    /**
     * Method to act on failed GitHub API call.
     */
    void failedDataRetrieval();
}
