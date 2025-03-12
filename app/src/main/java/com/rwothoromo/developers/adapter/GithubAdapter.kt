package com.rwothoromo.developers.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rwothoromo.developers.adapter.GithubAdapter.ViewHolder
import com.rwothoromo.developers.model.GithubUser
import com.rwothoromo.developers.view.GithubUserProfile
import com.rwothoromo.devfinder.R

/**
 * GitHub Adapter method.
 *
 * @param githubUsers List of GitHub users
 * @param context Context
 */
class GithubAdapter(private val githubUsers: List<GithubUser>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Create new views
    override fun onCreateViewHolder(viewGroup: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.github_user_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    // Display contents of a created view
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val githubUser = githubUsers[position]

        viewHolder.itemView.setOnClickListener { view ->
            val intent = Intent(view.context, GithubUserProfile::class.java)
            intent.putExtra("githubUser", githubUser)
            context.startActivity(intent)
        }

        viewHolder.usernameTextView.text = githubUser.username
        Glide.with(context).load(githubUser.avatarUrl)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
                .into(viewHolder.userProfileImageView)
    }

    // Return the size/length of the Developer list
    override fun getItemCount(): Int {
        return githubUsers.size
    }

    /**
     * ViewHolder class for user data.
     */
    inner class ViewHolder
    /**
     * ViewHolder method.
     *
     * @param itemView View
     */
    internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var userProfileImageView = itemView.findViewById<ImageView>(R.id.userProfileImageView)!!
        var usernameTextView = itemView.findViewById<TextView>(R.id.usernameTextView)!!

    }
}
