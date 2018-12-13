package com.example.rwothoromo.developers.view

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.crashlytics.android.Crashlytics
import com.example.rwothoromo.developers.R
import com.example.rwothoromo.developers.adapter.GithubAdapter
import com.example.rwothoromo.developers.constants.Constants.EXTRA_DEVELOPER_LIST_STATE
import com.example.rwothoromo.developers.model.GithubUser
import com.example.rwothoromo.developers.presenter.GithubPresenter
import com.example.rwothoromo.developers.util.NetworkConnectivity
import com.example.rwothoromo.developers.util.NetworkConnectivity.isNetworkConnected
import io.fabric.sdk.android.Fabric

/**
 * MainActivity class with the Developer list.
 */
class MainActivity : AppCompatActivity(), GithubUserView {

    internal var githubUserListState: Parcelable? = null
    private var recyclerView: RecyclerView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private val githubPresenter = GithubPresenter(this)
    private var networkConnectivity: NetworkConnectivity? = null

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply { subtitle = "Location: Nairobi, Stack: Java" }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView!!.setHasFixedSize(true)
        swipeRefreshLayout = findViewById(R.id.swiperefresh)

        layoutManager = GridLayoutManager(this, 2)

        if (!isNetworkConnected(this)) {
            Snackbar.make(recyclerView!!, getString(R.string.failed_data_retrieval),
                    Snackbar.LENGTH_LONG).show()
        } else {
            githubPresenter.getGithubUsers()
        }
    }

    override fun onStart() {
        super.onStart()

        // Listen for a User's swipe-to-refresh action on the Developer list
        swipeRefreshLayout!!.setOnRefreshListener { reloadGithubUsers() }
    }

    /**
     * Save state of the Developer list.
     *
     * @param savedInstanceState a Bundle
     */
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState)

        // Save the list state to the bundle
        githubUserListState = layoutManager!!.onSaveInstanceState()
        savedInstanceState.putParcelable(EXTRA_DEVELOPER_LIST_STATE, githubUserListState)
    }

    /**
     * Restore state of the Developer list.
     *
     * @param savedInstanceState a Bundle
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        // Restore the list state and list-item positions from the bundle
        if (savedInstanceState != null) {
            githubUserListState = savedInstanceState.getParcelable(EXTRA_DEVELOPER_LIST_STATE)
        }
    }

    /**
     * Resume the activity with the saved Developer list state if any.
     */
    override fun onResume() {
        super.onResume()

        if (githubUserListState != null) {
            layoutManager!!.onRestoreInstanceState(githubUserListState)
        }
    }

    /**
     * Inflate the Developer list options menu.
     *
     * @param menu a Menu
     * @return Boolean
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        return true
    }

    /**
     * Switch between different user-selected actions.
     *
     * @param item a MenuItem
     * @return Boolean
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings ->
                // User chose the "Settings" item, show the app settings UI...
                return true

            R.id.action_search ->
                // User chose the "Search" action, set the toolbar to a search field
                return true

            R.id.action_refresh -> {
                // User chose the "Refresh" action, refresh the Developer list
                swipeRefreshLayout!!.isRefreshing = true // Enable the refresh icon
                reloadGithubUsers()
                return true
            }

            else ->
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item)
        }
    }

    /**
     * Show the Developer list once the data is ready.
     *
     * @param githubUsers a GitHub users list
     */
    override fun githubUsersReady(githubUsers: List<GithubUser>) {
        this.showGithubUsers(githubUsers)
    }

    /**
     * Show the status for when GitHub API data can't be accessed.
     */
    override fun failedDataRetrieval() {
        val snackbar = Snackbar.make(recyclerView!!, getString(R.string.failed_data_retrieval),
                Snackbar.LENGTH_LONG)
        snackbar.setAction(R.string.settings, SettingsListener()).duration = 15000
        snackbar.show()
    }

    /**
     * Add GitHub user details to the user list.
     *
     * @param githubUsers a GitHub user list
     */
    fun showGithubUsers(githubUsers: List<GithubUser>) {
        recyclerView!!.layoutManager = layoutManager
        val adapter = GithubAdapter(githubUsers, this)
        recyclerView!!.adapter = adapter
    }

    /**
     * Update Developer list with GitHub API data.
     */
    fun reloadGithubUsers() {

        customProgressDialog(this@MainActivity,
                getString(R.string.refreshing))

        githubPresenter.getGithubUsers()

        swipeRefreshLayout!!.isRefreshing = false // Disable the refresh icon
    }

    /**
     * ProgressDialog with customizable message.
     *
     * @param context a Context
     * @param message a message
     */
    fun customProgressDialog(context: Context, message: String) {
        // Add a progress dialogue
        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Status")
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    /**
     * SettingsListener class to send the user to the Android device settings.
     */
    private inner class SettingsListener : View.OnClickListener {

        override fun onClick(v: View) {
            startActivity(Intent(Settings.ACTION_SETTINGS))
        }
    }
}
