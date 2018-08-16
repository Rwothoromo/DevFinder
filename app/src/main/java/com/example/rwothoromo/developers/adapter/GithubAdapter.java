package com.example.rwothoromo.developers.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rwothoromo.developers.R;
import com.example.rwothoromo.developers.model.GithubUser;
import com.example.rwothoromo.developers.view.GithubUserProfile;

import java.util.List;

import static com.example.rwothoromo.developers.constants.Constants.EXTRA_GITHUB_USER_AVATAR;
import static com.example.rwothoromo.developers.constants.Constants.EXTRA_GITHUB_USER_URL;
import static com.example.rwothoromo.developers.constants.Constants.EXTRA_GITHUB_USER_USERNAME;

/**
 * GitHub Adapter class.
 */
public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.ViewHolder> {

    private List<GithubUser> githubUsers;

    private Context context;

    /**
     * GitHub Adapter method.
     *
     * @param githubUsers List of GitHub users
     * @param context Context
     */
    public GithubAdapter(List<GithubUser> githubUsers, Context context) {
        this.githubUsers = githubUsers;
        this.context = context;
    }

    // Create new views
    @NonNull
    @Override
    public GithubAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                       int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.github_user_list_item, null);
        return new GithubAdapter.ViewHolder(view);
    }

    // Display contents of a created view
    @Override
    public void onBindViewHolder(@NonNull GithubAdapter.ViewHolder viewHolder, int position) {
        final GithubUser githubUser = githubUsers.get(position);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), GithubUserProfile.class);
                intent.putExtra(EXTRA_GITHUB_USER_USERNAME, githubUser.getUsername());
                intent.putExtra(EXTRA_GITHUB_USER_AVATAR, githubUser.getAvatarUrl());
                intent.putExtra(EXTRA_GITHUB_USER_URL, githubUser.getHtmlUrl());
                context.startActivity(intent);
            }
        });

        viewHolder.usernameTextView.setText(githubUser.getUsername());
        Glide.with(context).load(githubUser.getAvatarUrl()).into(viewHolder.userProfileImageView);
    }

    // Return the size/length of the Developer list
    @Override
    public int getItemCount() {
        return githubUsers.size();
    }

    /**
     * ViewHolder class for user data.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView userProfileImageView;
        public TextView usernameTextView;
        public TextView urlTextView;

        /**
         * ViewHolder method.
         *
         * @param itemView View
         */
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            userProfileImageView = itemView.findViewById(R.id.userProfileImageView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            urlTextView = itemView.findViewById(R.id.profileUrlTextView);
        }
    }
}
