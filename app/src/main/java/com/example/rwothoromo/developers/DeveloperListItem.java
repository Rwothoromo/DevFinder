package com.example.rwothoromo.developers;

public class DeveloperListItem {

	private String photo;
	private String username;

	public DeveloperListItem(String photo, String username) {
		this.photo = photo;
		this.username = username;
	}

	/**
	 * Returns the profile picture of the Developer
	 *
	 * @return photo
	 */
	public String getPhoto() {
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
}
