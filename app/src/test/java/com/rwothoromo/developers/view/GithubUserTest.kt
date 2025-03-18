package com.rwothoromo.developers.view

import com.rwothoromo.developers.model.GithubUser
import com.rwothoromo.developers.presenter.GithubPresenter
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class GithubUserTest {

    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()!!

    @Mock
    internal var githubPresenter: GithubPresenter? = null

    @Mock
    private var githubUserView: GithubUserView? = null

    @Mock
    private val githubUsers: ArrayList<GithubUser>? = null

    @Test
    fun fetchGithubUsers() {
        githubUserView = object : GithubUserView {
            override fun githubUsersReady(githubUsers: List<GithubUser>) {}

            override fun failedDataRetrieval() {}
        }

        githubPresenter = GithubPresenter(githubUserView as GithubUserView)
        githubPresenter!!.getGithubUsers()

        Assert.assertNotNull(githubUsers)
    }
}
