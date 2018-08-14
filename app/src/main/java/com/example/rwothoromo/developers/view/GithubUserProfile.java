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

import java.util.Objects;

import static com.example.rwothoromo.developers.constants.Constants.EXTRA_GITHUB_USER_AVATAR;
import static com.example.rwothoromo.developers.constants.Constants.EXTRA_GITHUB_USER_URL;
import static com.example.rwothoromo.developers.constants.Constants.EXTRA_GITHUB_USER_USERNAME;

public class GithubUserProfile extends AppCompatActivity {

	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_github_user_profile);
		Objects.requireNonNull(getSupportActionBar()).setTitle("Developer Profile");

		Intent intent = getIntent();

		ImageView profileImageView = findViewById(R.id.userProfileImageView);
		Glide.with(getApplicationContext()).load(intent.getStringExtra(EXTRA_GITHUB_USER_AVATAR)).into(profileImageView);

		TextView usernameTextView = findViewById(R.id.usernameTextView);
		usernameTextView.setText(intent.getStringExtra(EXTRA_GITHUB_USER_USERNAME));

		TextView profileUrlTextView = findViewById(R.id.profileUrlTextView);
		profileUrlTextView.setText(intent.getStringExtra(EXTRA_GITHUB_USER_URL));
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

			case R.id.action_share:
				// User chose the "Share" action, launch the share intent
				return true;

			default:
				// If we got here, the user's action was not recognized.
				// Invoke the superclass to handle it.
				return super.onOptionsItemSelected(item);

		}
	}
}
