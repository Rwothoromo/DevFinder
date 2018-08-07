package com.example.rwothoromo.developers.model;

import com.google.gson.annotations.SerializedName;

public class GithubUser {
	private int id;

	@SerializedName("login")
	private String username;

	@SerializedName("avatar_url")
	private String avatarUrl;

	@SerializedName("html_url")
	private String htmlUrl;

	@SerializedName("organizations_url")
	private String organizationsUrl;

	/**
	 * Returns the GitHub user id
	 *
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the GitHub username
	 *
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Returns the GitHub user profile image URL
	 *
	 * @return avatarUrl
	 */
	public String getAvatarUrl() {
		return avatarUrl;
	}

	/**
	 * Returns the GitHub user profile URL
	 *
	 * @return htmlUrl
	 */
	public String getHtmlUrl() {
		return htmlUrl;
	}

	/**
	 * Returns the GitHub user organizations URL
	 *
	 * @return organizationsUrl
	 */
	public String getOrganizationsUrl() {
		return organizationsUrl;
	}
}
