package com.example.rwothoromo.developers.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents the GitHub User model.
 */
public class GithubUser implements Parcelable {

    public static final Creator<GithubUser> CREATOR
            = new Creator<GithubUser>() {
        public GithubUser createFromParcel(Parcel in) {
            return new GithubUser(in);
        }

        public GithubUser[] newArray(int size) {
            return new GithubUser[size];
        }
    };

    private int id;
    @SerializedName("login")
    private String username;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("html_url")
    private String htmlUrl;

    /**
     * Constructor for the GithubUser class.
     *
     * @param id user ID
     * @param username username
     * @param avatarUrl profile image URL
     * @param htmlUrl profile URL
     */
    public GithubUser(int id, String username, String avatarUrl, String htmlUrl) {
        this.id = id;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.htmlUrl = htmlUrl;
    }

    /**
     * Parcel in the Github User.
     *
     * @param in a Parcelable GitHub user object
     */
    public GithubUser(Parcel in) {
        id = in.readInt();
        username = in.readString();
        avatarUrl = in.readString();
        htmlUrl = in.readString();
    }

    /**
     * Returns the GitHub user id.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the GitHub username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the GitHub user profile image URL.
     *
     * @return avatarUrl
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Returns the GitHub user profile URL.
     *
     * @return htmlUrl
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Child classes can return different values with this method.
     *
     * @return integer
     */
    public int describeContents() {
        return 0;
    }

    /**
     * Write data to the Parcel.
     *
     * @param out a Parcel
     * @param flags flags
     */
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(username);
        out.writeString(avatarUrl);
        out.writeString(htmlUrl);
    }
}
