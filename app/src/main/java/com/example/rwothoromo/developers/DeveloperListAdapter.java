package com.example.rwothoromo.developers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DeveloperListAdapter extends RecyclerView.Adapter<DeveloperListAdapter.ViewHolder> {

	private List<DeveloperListItem> developerList;
	private Context context;

	DeveloperListAdapter(List<DeveloperListItem> developerList, Context context) {
		this.developerList = developerList;
		this.context = context;
	}

	// Create new views
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
		View view = LayoutInflater.from(context)
								.inflate(R.layout.developer_list_item, null);
		return new ViewHolder(view);
	}

	// Display contents of a created view
	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
		DeveloperListItem developer = developerList.get(position);

		// Set image
		// viewHolder.photoImageView
		viewHolder.usernameTextView.setText(developer.getUsername());
	}

	// Return the size/length of the Developer list
	@Override
	public int getItemCount() {
		return developerList.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		public ImageView photoImageView;
		public TextView usernameTextView;

		ViewHolder(@NonNull View itemView) {
			super(itemView);

			photoImageView = itemView.findViewById(R.id.photoImageView);
			usernameTextView = itemView.findViewById(R.id.usernameTextView);
		}
	}
}
