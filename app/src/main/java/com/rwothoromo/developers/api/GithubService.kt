package com.rwothoromo.developers.api

import com.rwothoromo.developers.models.GithubUsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This interface holds the GitHub API endpoints.
 */
interface GithubService {
    /**
     * Endpoint to get all developers on GitHub, located in a City and Use Kotlin.
     * Refine Your Search with Qualifiers:
     * in:
     *   This qualifier specifies which fields to search within the user's profile.
     *     Example: in:name searches for users with a specific name.
     *     Example: in:email searches for users with a specific email.
     * repos:
     *   Filters users based on the number of repositories they have.
     *     Example: repos:>10 finds users with more than 10 repositories.
     * location:
     *   Filters users by the location indicated in their profile.
     *     Example: location:Kampala finds users who have Kampala as their location.
     * language:
     *   Searches for users that have repositories that match a certain language.
     *     Example: language:python finds users who have repositories that use Python.
     * created:
     *   Filters users based on when they joined.
     *     Example: created:<2023-01-01 finds users who joined before January 1, 2023.
     * followers:
     *   Filters users based on the number of followers they have.
     *     Example: followers:>100 finds users with more than 100 followers.
     *
     * Examples of Combined Queries:
     * 1. Search for users with "john" in their name, limiting to users only:
     *   - https://api.github.com/search/users?q=john in:name type:user
     * 2. Search for users with "john" in their name, limiting to users only, with more than 30 repositories and 10 followers:
     *   - https://api.github.com/search/users?q=john in:name type:user repos:>30 followers:>10
     * 3. Search for users with Kampala as their location:
     *   - https://api.github.com/search/users?q=location:Kampala
     * 4. Search for users who have repositories that use Python:
     *   - https://api.github.com/search/users?q=language:python
     *
     * This also works and returns 25 records:
     * @GET("/search/users?q=type:user+language:kotlin+location:kampala")
     * suspend fun getGithubUsersByLocation(): GithubUsersResponse
     *
     * @return API response
     */
    @GET("/search/users")
    suspend fun getGithubUsersByLocation(@Query("q") githubUserFilter: String): GithubUsersResponse

    /**
     * Endpoint to get all repositories on GitHub, with a given Tech Stack.
     *
     * @return API response
     */
    @GET("/search/search/repositories")
    suspend fun getGithubReposByTopic(@Query("topic") techStack: String): GithubUsersResponse

//    TODO: Implement filter/search by name, username, city, country, tech stack and add details in profile
}
