package com.example.rwothoromo.developers.view;

import com.example.rwothoromo.developers.model.GithubUser;

import java.util.List;

/**
 * This class represents the GitHub User view interface.
 */
public interface GithubUserView {

	void githubUsersReady(List<GithubUser> githubUsers);
	void failedDataRetrieval();
}
