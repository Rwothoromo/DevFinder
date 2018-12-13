package com.example.rwothoromo.developers.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * This class represents the GitHub User model.
 */
class GithubUser : Parcelable {

    /**
     * Returns the GitHub user id.
     *
     * @return id
     */
    var id: Int = 0
        private set
    /**
     * Returns the GitHub username.
     *
     * @return username
     */
    @SerializedName("login")
    var username: String? = null
        private set
    /**
     * Returns the GitHub user profile image URL.
     *
     * @return avatarUrl
     */
    @SerializedName("avatar_url")
    var avatarUrl: String? = null
        private set
    /**
     * Returns the GitHub user profile URL.
     *
     * @return htmlUrl
     */
    @SerializedName("html_url")
    var htmlUrl: String? = null
        private set

    /**
     * Constructor for the GithubUser class.
     *
     * @param id user ID
     * @param username username
     * @param avatarUrl profile image URL
     * @param htmlUrl profile URL
     */
    constructor(id: Int, username: String, avatarUrl: String, htmlUrl: String) {
        this.id = id
        this.username = username
        this.avatarUrl = avatarUrl
        this.htmlUrl = htmlUrl
    }

    /**
     * Parcel in the Github User.
     *
     * @param in a Parcelable GitHub user object
     */
    constructor(`in`: Parcel) {
        id = `in`.readInt()
        username = `in`.readString()
        avatarUrl = `in`.readString()
        htmlUrl = `in`.readString()
    }

    /**
     * Child classes can return different values with this method.
     *
     * @return integer
     */
    override fun describeContents(): Int {
        return 0
    }

    /**
     * Write data to the Parcel.
     *
     * @param out a Parcel
     * @param flags flags
     */
    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeInt(id)
        out.writeString(username)
        out.writeString(avatarUrl)
        out.writeString(htmlUrl)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<GithubUser> = object : Parcelable.Creator<GithubUser> {
            override fun createFromParcel(`in`: Parcel): GithubUser {
                return GithubUser(`in`)
            }

            override fun newArray(size: Int): Array<GithubUser?> {
                return arrayOfNulls(size)
            }
        }
    }
}
