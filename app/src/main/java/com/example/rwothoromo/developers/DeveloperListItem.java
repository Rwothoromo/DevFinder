package com.example.rwothoromo.developers;

public class DeveloperListItem {

	private int photo;
	private String username;
	private String profileUrl;

	DeveloperListItem(int photo, String username, String profileUrl) {
		this.photo = photo;
		this.username = username;
		this.profileUrl = profileUrl;
	}

	/**
	 * Returns the profile picture of the Developer
	 *
	 * @return photo
	 */
	public int getPhoto() {
		return photo;
	}

	/**
	 * Returns the Username of the Developer
	 *
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Returns the Profile URL of the Developer
	 *
	 * @return profileUrl
	 */
	public String getProfileUrl() {
		return profileUrl;
	}
}
