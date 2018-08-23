package com.example.rwothoromo.developers.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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
import java.util.Objects;

import static com.example.rwothoromo.developers.constants.Constants.EXTRA_DEVELOPER_LIST_STATE;

/**
 * MainActivity class with the Developer list.
 */
public class MainActivity extends AppCompatActivity implements GithubUserView {

    Parcelable githubUserListState = null;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GithubPresenter githubPresenter = new GithubPresenter(this);

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setSubtitle("Location: Nairobi, Stack: Java");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);

        layoutManager = new GridLayoutManager(this, 2);

        githubPresenter.getGithubUsers();

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Listen for a User's swipe-to-refresh action on the Developer list
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        reloadGithubUsers();
                    }
                }
        );
    }

    /**
     * Save state of the Developer list.
     *
     * @param savedInstanceState a Bundle
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);

        // Save the list state to the bundle
        githubUserListState = layoutManager.onSaveInstanceState();
        savedInstanceState.putParcelable(EXTRA_DEVELOPER_LIST_STATE, githubUserListState);
    }

    /**
     * Restore state of the Developer list.
     *
     * @param savedInstanceState a Bundle
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Restore the list state and list-item positions from the bundle
        if (savedInstanceState != null) {
            githubUserListState = savedInstanceState.getParcelable(EXTRA_DEVELOPER_LIST_STATE);
        }
    }

    /**
     * Resume the activity with the saved Developer list state if any.
     */
    @Override
    protected void onResume() {
        super.onResume();

        if (githubUserListState != null) {
            layoutManager.onRestoreInstanceState(githubUserListState);
        }
    }

    /**
     * Inflate the Developer list options menu.
     *
     * @param menu a Menu
     * @return Boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        return true;
    }

    /**
     * Switch between different user-selected actions.
     *
     * @param item a MenuItem
     * @return Boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_search:
                // User chose the "Search" action, set the toolbar to a search field
                return true;

            case R.id.action_refresh:
                // User chose the "Refresh" action, refresh the Developer list
                swipeRefreshLayout.setRefreshing(true); // Enable the refresh icon
                reloadGithubUsers();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * Show the Developer list once the data is ready.
     *
     * @param githubUsers a GitHub users list
     */
    @Override
    public void githubUsersReady(List<GithubUser> githubUsers) {
        this.showGithubUsers(githubUsers);
    }

    /**
     * Show the status for when GitHub API data can't be accessed.
     */
    @Override
    public void failedDataRetrieval() {
        customProgressDialog(MainActivity.this,
                getString(R.string.failed_data_retrieval));
    }

    /**
     * Add GitHub user details to the user list.
     *
     * @param githubUsers a GitHub user list
     */
    public void showGithubUsers(List<GithubUser> githubUsers) {
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new GithubAdapter(githubUsers, this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Update Developer list with GitHub API data.
     */
    public void reloadGithubUsers() {

        customProgressDialog(MainActivity.this,
                getString(R.string.refreshing));

        githubPresenter.getGithubUsers();

        swipeRefreshLayout.setRefreshing(false); // Disable the refresh icon
    }

    /**
     * ProgressDialog with customizable message.
     *
     * @param context a Context
     * @param message a message
     */
    public void customProgressDialog(Context context, String message) {
        // Add a progress dialogue
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Status");
        progressDialog.setMessage(message);
        progressDialog.show();
    }
}
