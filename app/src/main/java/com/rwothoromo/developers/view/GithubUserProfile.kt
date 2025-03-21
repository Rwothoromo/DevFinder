package com.rwothoromo.developers.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rwothoromo.developers.model.GithubUser
import com.rwothoromo.devfinder.R

/**
 * GitHub user profile class.
 */
class GithubUserProfile : AppCompatActivity() {

    private var githubUsername: String? = null
    private var githubUserURL: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_user_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        val githubUser = intent.getParcelableExtra<GithubUser>("githubUser")
        githubUsername = githubUser?.username
        githubUserURL = githubUser?.htmlUrl

        val profileImageView = findViewById<ImageView>(R.id.userProfileImageView)
        Glide.with(applicationContext).load(githubUser?.avatarUrl).into(profileImageView)

        val usernameTextView = findViewById<TextView>(R.id.usernameTextView)
        usernameTextView.text = githubUsername

        val profileUrlTextView = findViewById<TextView>(R.id.profileUrlTextView)
        profileUrlTextView.text = githubUserURL
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.profile_options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            android.R.id.home -> {
                // User chose the "Back" action, send them back to the Developer list
                finish()
                return true
            }

            R.id.action_share -> {
                // User chose the "Share" action, launch the share intent
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    getString(R.string.profile_share_description, githubUsername, githubUserURL)
                )
                startActivity(
                    Intent.createChooser(
                        shareIntent,
                        getString(R.string.profile_share_title)
                    )
                )
                return true
            }

            else ->
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item)
        }
    }
}
