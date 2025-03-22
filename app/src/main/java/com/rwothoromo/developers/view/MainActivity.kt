package com.rwothoromo.developers.view

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.provider.Settings
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.rwothoromo.developers.adapter.GithubAdapter
import com.rwothoromo.developers.constants.Constants.EXTRA_GITHUB_USER_LIST_STATE
import com.rwothoromo.developers.models.GithubUser
import com.rwothoromo.developers.models.GithubUsersResponse
import com.rwothoromo.developers.util.NetworkConnectivity.isNetworkConnected
import com.rwothoromo.developers.viewmodels.GithubUserViewModel
import com.rwothoromo.devfinder.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


/**
 * MainActivity class with the Developer list.
 */
class MainActivity : AppCompatActivity() {

    private var githubUserListParcelable: Parcelable? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var githubUserViewModel: GithubUserViewModel
    private lateinit var alertDialog: AlertDialog
    private var userType: String = "user"
    private var city: String = "Kampala"
    private var techStack: String = "All"
    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    @RequiresPermission(Manifest.permission.DISABLE_KEYGUARD)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply { subtitle = getString(R.string.app_subtitle, city, techStack) }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        layoutManager = GridLayoutManager(this, 2)

        githubUserViewModel = ViewModelProvider(this)[GithubUserViewModel::class.java]

        if (!isNetworkConnected(this)) {
            Snackbar.make(
                recyclerView, getString(R.string.failed_data_retrieval),
                Snackbar.LENGTH_LONG
            )
                .setAction(getString(R.string.close)) { }
                .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                .show()
        } else {
            githubUserViewModel.data.observe(this) { githubUsersResponse ->
                // Update UI with fetchedData
                githubUsersReady(githubUsersResponse)
            }

            githubUserViewModel.error.observe(this) { errorMessage ->
                errorMessage?.let { failedDataRetrieval(it) }
            }

            githubUserViewModel.isFetching.observe(this) { isFetching ->
                if (isFetching) {
                    customAlertDialog(getString(R.string.refreshing))
                }
            }

            githubUserViewModel.getGithubUsersData(userType, city, techStack) // Trigger data fetch
        }
    }

    override fun onStart() {
        super.onStart()

        // Listen for a User's swipe-to-refresh action on the Developer list
        swipeRefreshLayout.setOnRefreshListener { reloadGithubUsers() }
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
        layoutManager.onSaveInstanceState()?.let { userListState ->
            githubUserListParcelable = userListState
            outState.putParcelable(EXTRA_GITHUB_USER_LIST_STATE, githubUserListParcelable)
        }
    }

    /**
     * Restore state of the Developer list.
     *
     * @param savedInstanceState a Bundle
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // Restore the list state and list-item positions from the bundle
        githubUserListParcelable = savedInstanceState.getParcelable(EXTRA_GITHUB_USER_LIST_STATE)
    }

    /**
     * Resume the activity with the saved Developer list state if any.
     */
    override fun onResume() {
        super.onResume()

        layoutManager.onRestoreInstanceState(githubUserListParcelable)
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
                    recyclerView, getString(R.string.feature_pending),
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
                    recyclerView, getString(R.string.feature_pending),
                    Snackbar.LENGTH_LONG
                )
                    .setAction(getString(R.string.close)) { }
                    .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                    .show()
                return true
            }

            R.id.action_refresh -> {
                // User chose the "Refresh" action, refresh the Developer list
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
     * @param githubUsersResponse with a GitHub users list
     */
    private fun githubUsersReady(githubUsersResponse: GithubUsersResponse) {
        this.showGithubUsers(githubUsersResponse.githubUserList)
        Snackbar.make(
            recyclerView,
            getString(R.string.successful_data_retrieval, githubUsersResponse.count),
            Snackbar.LENGTH_LONG
        )
    }

    /**
     * Show the status for when GitHub API data can't be accessed.
     */
    private fun failedDataRetrieval(errorMessage: String) {
        val snackbar = Snackbar.make(
            recyclerView, getString(R.string.failed_data_retrieval, errorMessage),
            Snackbar.LENGTH_LONG
        )
        if (errorMessage.contains("HTTP 422")) {
            snackbar.duration = 30000
        } else {
            snackbar.setAction(R.string.settings, SettingsListener()).duration = 15000
        }
        snackbar.show()
    }

    /**
     * Add GitHub user details to the user list.
     *
     * @param githubUsers a GitHub user list
     */
    private fun showGithubUsers(githubUsers: List<GithubUser>) {
        recyclerView.layoutManager = layoutManager
        val adapter = GithubAdapter(githubUsers, this)
        recyclerView.adapter = adapter
    }

    /**
     * Update Developer list with GitHub API data.
     */
    private fun reloadGithubUsers() {
        coroutineScope.launch {
            githubUserViewModel.getGithubUsersData(userType, city, techStack)
        }
    }

    /**
     * ProgressDialog with customizable message.
     *
     * @param context a Context
     * @param message a message
     */
    private fun customAlertDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.status))
//        builder.setMessage(message)
//        builder.setPositiveButton("OK") { dialog, which -> dialog.dismiss() }

        val loadingView = LayoutInflater.from(this).inflate(R.layout.loading, null, true)
        val messageView: TextView = loadingView.findViewById(R.id.message)
        messageView.text = message

        builder.setView(loadingView)
        builder.setCancelable(true)

        // Create the AlertDialog
        alertDialog = builder.create()
        alertDialog.show()

        // Schedule the dismissal after a certain delay
        val handler = Handler()
        handler.postDelayed({
            if (alertDialog.isShowing) {
                alertDialog.dismiss()
            }
        }, 5000.toLong())  // 5 seconds
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
