package com.example.rwothoromo.developers.view;

import com.example.rwothoromo.developers.model.GithubUser;
import com.example.rwothoromo.developers.presenter.GithubPresenter;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

public class GithubUserTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    GithubPresenter githubPresenter;
    @Mock
    private GithubUserView githubUserView;
    @Mock
    private ArrayList<GithubUser> githubUsers;

    @Test
    public void fetchGithubUsers() {
        githubUserView = new GithubUserView() {
            @Override
            public void githubUsersReady(List<GithubUser> githubUsers) {
            }

            @Override
            public void failedDataRetrieval() {
            }
        };

        githubPresenter = new GithubPresenter(githubUserView);
        githubPresenter.getGithubUsers();

        Assert.assertNotNull(githubUsers);
    }
}
