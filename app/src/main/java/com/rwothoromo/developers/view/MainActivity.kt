package com.rwothoromo.developers.view

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.rwothoromo.developers.adapter.GithubAdapter
import com.rwothoromo.developers.constants.Constants.EXTRA_DEVELOPER_LIST_STATE
import com.rwothoromo.developers.model.GithubUser
import com.rwothoromo.developers.presenter.GithubPresenter
import com.rwothoromo.developers.util.NetworkConnectivity
import com.rwothoromo.developers.util.NetworkConnectivity.isNetworkConnected
import com.rwothoromo.devfinder.R


/**
 * MainActivity class with the Developer list.
 */
class MainActivity : AppCompatActivity(), GithubUserView {

    private var githubUserListState: Parcelable? = null
    private var recyclerView: RecyclerView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private val githubPresenter = GithubPresenter(this)
    private var networkConnectivity: NetworkConnectivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply { subtitle = "Location: Kampala, Stack: Java" }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView!!.setHasFixedSize(true)
        swipeRefreshLayout = findViewById(R.id.swiperefresh)

        layoutManager = GridLayoutManager(this, 2)

        if (!isNetworkConnected(this)) {
            Snackbar.make(
                recyclerView!!, getString(R.string.failed_data_retrieval),
                Snackbar.LENGTH_LONG
            )
                .setAction(getString(R.string.close)) { }
                .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                .show()
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
     * @param outState a Bundle
     */
    override fun onSaveInstanceState(outState: Bundle) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState)

        // Save the list state to the bundle
        githubUserListState = layoutManager!!.onSaveInstanceState()
        outState.putParcelable(EXTRA_DEVELOPER_LIST_STATE, githubUserListState)
    }

    /**
     * Restore state of the Developer list.
     *
     * @param savedInstanceState a Bundle
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // Restore the list state and list-item positions from the bundle
        githubUserListState = savedInstanceState.getParcelable(EXTRA_DEVELOPER_LIST_STATE)
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
            R.id.action_settings -> {
                // User chose the "Settings" item, show the app settings UI...
                Snackbar.make(
                    recyclerView!!, getString(R.string.feature_pending),
                    Snackbar.LENGTH_LONG
                )
                    .setAction(getString(R.string.close)) { }
                    .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                    .show()
                return true
            }

            R.id.action_search -> {
                // User chose the "Search" action, set the toolbar to a search field
                Snackbar.make(
                    recyclerView!!, getString(R.string.feature_pending),
                    Snackbar.LENGTH_LONG
                )
                    .setAction(getString(R.string.close)) { }
                    .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                    .show()
                return true
            }

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
        val snackbar = Snackbar.make(
            recyclerView!!, getString(R.string.failed_data_retrieval),
            Snackbar.LENGTH_LONG
        )
        snackbar.setAction(R.string.settings, SettingsListener()).duration = 15000
        snackbar.show()
    }

    /**
     * Add GitHub user details to the user list.
     *
     * @param githubUsers a GitHub user list
     */
    private fun showGithubUsers(githubUsers: List<GithubUser>) {
        recyclerView!!.layoutManager = layoutManager
        val adapter = GithubAdapter(githubUsers, this)
        recyclerView!!.adapter = adapter
    }

    /**
     * Update Developer list with GitHub API data.
     */
    private fun reloadGithubUsers() {

        customProgressDialog(
            this@MainActivity,
            getString(R.string.refreshing)
        )

        githubPresenter.getGithubUsers()

        swipeRefreshLayout!!.isRefreshing = false // Disable the refresh icon
    }

    /**
     * ProgressDialog with customizable message.
     *
     * @param context a Context
     * @param message a message
     */
    private fun customProgressDialog(context: Context, message: String) {
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
