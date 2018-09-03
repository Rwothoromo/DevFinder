package com.example.rwothoromo.developers.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rwothoromo.developers.R;
import com.example.rwothoromo.developers.model.GithubUser;

import java.util.Objects;

/**
 * GitHub user profile class.
 */
public class GithubUserProfile extends AppCompatActivity {

    private String githubUsername;
    private String githubUserURL;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_user_profile);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        GithubUser githubUser = intent.getParcelableExtra("githubUser");
        githubUsername = githubUser.getUsername();
        githubUserURL = githubUser.getHtmlUrl();

        ImageView profileImageView = findViewById(R.id.userProfileImageView);
        Glide.with(getApplicationContext()).load(githubUser.getAvatarUrl()).into(profileImageView);

        TextView usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText(githubUsername);

        TextView profileUrlTextView = findViewById(R.id.profileUrlTextView);
        profileUrlTextView.setText(githubUserURL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // User chose the "Back" action, send them back to the Developer list
                finish();
                return true;

            case R.id.action_share:
                // User chose the "Share" action, launch the share intent
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer @"
                        + githubUsername + ", " + githubUserURL);
                startActivity(Intent.createChooser(shareIntent, "Share this developer"));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
