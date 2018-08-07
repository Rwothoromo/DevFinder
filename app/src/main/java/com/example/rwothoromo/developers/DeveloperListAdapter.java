package com.example.rwothoromo.developers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DeveloperListAdapter extends RecyclerView.Adapter<DeveloperListAdapter.ViewHolder> {

	public static final String DEVELOPER_USERNAME = "com.example.rwothoromo.developers.DEVELOPER_USERNAME";
	public static final String DEVELOPER_PHOTO = "com.example.rwothoromo.developers.DEVELOPER_PHOTO";
	public static final String DEVELOPER_URL = "com.example.rwothoromo.developers.DEVELOPER_URL";

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
		final DeveloperListItem developer = developerList.get(position);

		viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), DeveloperProfile.class);
				intent.putExtra(DEVELOPER_USERNAME, developer.getUsername());
				intent.putExtra(DEVELOPER_PHOTO, developer.getPhoto());
				intent.putExtra(DEVELOPER_URL, developer.getProfileUrl());
				context.startActivity(intent);
			}
		});

		viewHolder.usernameTextView.setText(developer.getUsername());
		viewHolder.userProfileImageView.setImageResource(developer.getPhoto());

	}

	// Return the size/length of the Developer list
	@Override
	public int getItemCount() {
		return developerList.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		public ImageView userProfileImageView;
		public TextView usernameTextView;
		public TextView urlTextView;

		ViewHolder(@NonNull View itemView) {
			super(itemView);

			userProfileImageView = itemView.findViewById(R.id.userProfileImageView);
			usernameTextView = itemView.findViewById(R.id.usernameTextView);
			urlTextView = itemView.findViewById(R.id.profileUrlTextView);
		}
	}
}
