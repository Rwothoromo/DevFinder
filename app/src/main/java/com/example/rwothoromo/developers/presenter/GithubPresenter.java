package com.example.rwothoromo.developers.presenter;

import com.example.rwothoromo.developers.model.GithubUser;
import com.example.rwothoromo.developers.model.GithubUsersResponse;
import com.example.rwothoromo.developers.service.GithubService;
import com.example.rwothoromo.developers.view.GithubUserView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class is a service that leverages the GithubApi interface.
 * It represents a view of the data retrieved.
 */
public class GithubPresenter {

	private GithubUserView githubUserView;
	private GithubService githubService;

	public GithubPresenter(GithubUserView githubUserView) {
		this.githubUserView = githubUserView;

		if (this.githubService == null) {
			this.githubService = new GithubService();
		}
	}

	public void getGithubUsers() {
		githubService.getAPI().getResults()
				.enqueue(new Callback<GithubUsersResponse>() {
					@Override
					public void onResponse(Call<GithubUsersResponse> call, Response<GithubUsersResponse> response) {

						GithubUsersResponse githubUsersResponse = response.body();

						if (githubUsersResponse != null && githubUsersResponse.getResult() != null) {
							List<GithubUser> githubUsers = githubUsersResponse.getResult();
							githubUserView.githubUsersReady(githubUsers);
						}
					}

					@Override
					public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
						githubUserView.failedDataRetrieval();
					}
				});
	}
}
