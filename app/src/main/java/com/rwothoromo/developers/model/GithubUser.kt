package com.rwothoromo.developers.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * This class represents the GitHub User model.
 */
class GithubUser : Parcelable {

    @SerializedName("login")
    var username: String = ""

    var id: Int = 0
    var node_id: String = ""

    @SerializedName("avatar_url")
    var avatarUrl: String = ""

    var gravatar_id: String = ""
    var url: String = ""

    @SerializedName("html_url")
    var htmlUrl: String = ""

    var followers_url: String = ""
    var following_url: String = ""
    var gists_url: String = ""
    var starred_url: String = ""
    var subscriptions_url: String = ""
    var organizations_url: String = ""
    var repos_url: String = ""
    var events_url: String = ""
    var received_events_url: String = ""
    var type: String = ""
    var user_view_type: String = ""
    var site_admin: Boolean = false
    var score: Float = 0.0F

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
