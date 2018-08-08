package com.example.rwothoromo.developers.presenter;

import com.example.rwothoromo.developers.model.Data;
import com.example.rwothoromo.developers.model.GithubUser;
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

	public GithubPresenter(GithubUserView view) {
		this.githubUserView = view;

		if (this.githubService == null) {
			this.githubService = new GithubService();
		}
	}

	public void getGithubUsers() {
		githubService.getAPI().getResults()
				.enqueue(new Callback<Data>() {
					@Override
					public void onResponse(Call<Data> call, Response<Data> response) {
						Data data = response.body();

						if (data != null && data.getGithubUsersResponse() != null) {
							List<GithubUser> result = data.getGithubUsersResponse().getResult();
							githubUserView.githubUsersReady(result);
						}
					}

					@Override
					public void onFailure(Call<Data> call, Throwable t) {

						githubUserView.failedDataRetrieval();

					}
				});
	}
}
