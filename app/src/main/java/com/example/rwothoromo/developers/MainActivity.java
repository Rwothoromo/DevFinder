package com.example.rwothoromo.developers;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	public static final String EXTRA_DEVELOPER_LIST_STATE = "com.example.rwothoromo.developers.EXTRA_DEVELOPER_LIST_STATE";
	Parcelable developerListState = null;
	private RecyclerView.LayoutManager layoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		RecyclerView recyclerView = findViewById(R.id.recyclerView);
		recyclerView.setHasFixedSize(true);

		layoutManager = new LinearLayoutManager(this);

		List<DeveloperListItem> developers = new ArrayList<>();

		for (int i = 0; i <= 10; i++) {
			DeveloperListItem developer = new DeveloperListItem(
					R.mipmap.ic_launcher,
					"Username" + (i + 1),
					"https://github.com/Username" + (i + 1)
			);

			developers.add(developer);
		}

		RecyclerView.Adapter adapter = new DeveloperListAdapter(developers, this);
		recyclerView.setAdapter(adapter);
	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		// Always call the superclass so it can save the view hierarchy state
		super.onSaveInstanceState(savedInstanceState);

		// Save the list state to the bundle
		developerListState = layoutManager.onSaveInstanceState();
		savedInstanceState.putParcelable(EXTRA_DEVELOPER_LIST_STATE, developerListState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		// Restore the list state and list-item positions from the bundle
		if (savedInstanceState != null)
			developerListState = savedInstanceState.getParcelable(EXTRA_DEVELOPER_LIST_STATE);
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (developerListState != null)
			layoutManager.onRestoreInstanceState(developerListState);
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

}
