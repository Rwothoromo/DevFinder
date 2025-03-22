package com.rwothoromo.developers.models

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * This class represents the GitHub User model.
 */
class GithubUser : Parcelable {

    @SerializedName("login")
    var username: String

    var id: Int = 0

    @SerializedName("node_id")
    var nodeId: String = ""

    @SerializedName("avatar_url")
    var avatarUrl: String = ""

    @SerializedName("gravatar_id")
    var gravatarId: String = ""

    var url: String = ""

    @SerializedName("html_url")
    var htmlUrl: String = ""

    @SerializedName("followers_url")
    var followersUrl: String = ""

    @SerializedName("following_url")
    var followingUrl: String = ""

    @SerializedName("gists_url")
    var gistsUrl: String = ""

    @SerializedName("starred_url")
    var starredUrl: String = ""

    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String = ""

    @SerializedName("organizations_url")
    var organizationsUrl: String = ""

    @SerializedName("repos_url")
    var reposUrl: String = ""

    @SerializedName("events_url")
    var eventsUrl: String = ""

    @SerializedName("received_events_url")
    var receivedEventsUrl: String = ""

    var type: String = ""

    @SerializedName("user_view_type")
    var userViewType: String = ""

    @SerializedName("site_admin")
    var isSiteAdmin: Boolean = false

    var score: Double = 0.0

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
        username = `in`.readString().toString()
        avatarUrl = `in`.readString().toString()
        htmlUrl = `in`.readString().toString()
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
