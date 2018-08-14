package com.example.rwothoromo.developers.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.rwothoromo.developers.R;
import com.example.rwothoromo.developers.adapter.GithubAdapter;
import com.example.rwothoromo.developers.model.GithubUser;
import com.example.rwothoromo.developers.presenter.GithubPresenter;

import java.util.List;

import static com.example.rwothoromo.developers.constants.Constants.EXTRA_DEVELOPER_LIST_STATE;

public class MainActivity extends AppCompatActivity implements GithubUserView {

	Parcelable githubUserListState = null;
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		recyclerView = findViewById(R.id.recyclerView);
		recyclerView.setHasFixedSize(true);

		layoutManager = new LinearLayoutManager(this);

		GithubPresenter githubPresenter = new GithubPresenter(this);
		githubPresenter.getGithubUsers();

	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		// Always call the superclass so it can save the view hierarchy state
		super.onSaveInstanceState(savedInstanceState);

		// Save the list state to the bundle
		githubUserListState = layoutManager.onSaveInstanceState();
		savedInstanceState.putParcelable(EXTRA_DEVELOPER_LIST_STATE, githubUserListState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		// Restore the list state and list-item positions from the bundle
		if (savedInstanceState != null)
			githubUserListState = savedInstanceState.getParcelable(EXTRA_DEVELOPER_LIST_STATE);
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (githubUserListState != null)
			layoutManager.onRestoreInstanceState(githubUserListState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_settings:
				// User chose the "Settings" item, show the app settings UI...
				return true;

			case R.id.action_search:
				// User chose the "Search" action, set the toolbar to a search field
				return true;

			default:
				// If we got here, the user's action was not recognized.
				// Invoke the superclass to handle it.
				return super.onOptionsItemSelected(item);

		}
	}

	@Override
	public void githubUsersReady(List<GithubUser> githubUsers) {
		this.showGithubUsers(githubUsers);
	}

	@Override
	public void failedDataRetrieval() {

	}

	/**
	 * Add GitHub user details to the user list
	 *
	 * @param githubUsers, List
	 */
	public void showGithubUsers(List<GithubUser> githubUsers) {
		recyclerView.setLayoutManager(layoutManager);
		RecyclerView.Adapter adapter = new GithubAdapter(githubUsers, this);
		recyclerView.setAdapter(adapter);
	}
}
