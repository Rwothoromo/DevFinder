package com.rwothoromo.developers.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rwothoromo.developers.api.GithubApiClient
import com.rwothoromo.developers.models.GithubUsersResponse
import kotlinx.coroutines.launch

class GithubUserViewModel : ViewModel() {
    private val githubService = GithubApiClient.githubService

    private val _data = MutableLiveData<GithubUsersResponse>()  // Or MutableStateFlow
    val data: LiveData<GithubUsersResponse> = _data // Or StateFlow

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getGithubUsersData(userType = "user", city = "Kampala", techStack = "All")
    }

    fun getGithubUsersData(userType: String, city: String, techStack: String = "All") {
        viewModelScope.launch {
            try {
                var githubUserFilter = "type:${userType.lowercase()} location:${city.lowercase()}"
                if (techStack != "All") {
                    githubUserFilter += " language:${techStack.lowercase()}" // 25 Records
                }
                val response: GithubUsersResponse = githubService.getGithubUsersByLocation(
                    githubUserFilter = githubUserFilter
                )

                _data.value = response // Update LiveData
            } catch (e: Exception) {
                _error.value = e.message
            }

        }
    }
}