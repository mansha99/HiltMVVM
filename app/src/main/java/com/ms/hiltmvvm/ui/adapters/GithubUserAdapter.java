package com.ms.hiltmvvm.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ms.hiltmvvm.R;
import com.ms.hiltmvvm.io.models.GithubUserModel;

import java.util.ArrayList;
import java.util.List;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.GithubUserViewHolder> {
    private AsyncListDiffer<GithubUserModel> mAsyncListDiffer;

    public static class GithubUserViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public GithubUserViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.userName);
        }

        public TextView getTextView() {
            return textView;
        }
    }


    public GithubUserAdapter() {
        DiffUtil.ItemCallback<GithubUserModel> diffUtilCallback = new DiffUtil.ItemCallback<GithubUserModel>() {

            @Override
            public boolean areItemsTheSame(@NonNull GithubUserModel newUser, @NonNull GithubUserModel oldUser) {
                return newUser.getLogin().equals(oldUser.getLogin());
            }

            @SuppressLint("DiffUtilEquals")
            @Override
            public boolean areContentsTheSame(@NonNull GithubUserModel newUser, @NonNull GithubUserModel oldUser) {
                return newUser.equals(oldUser);
            }
        };
        mAsyncListDiffer = new AsyncListDiffer<>(this, diffUtilCallback);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GithubUserViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_user, viewGroup, false);

        return new GithubUserViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(GithubUserViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(mAsyncListDiffer.getCurrentList().get(position).getLogin());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mAsyncListDiffer.getCurrentList().size();
    }
    public void updateList(List<GithubUserModel> newList) {
        mAsyncListDiffer.submitList(newList);
    }
}

