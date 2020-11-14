package com.ritchie.movieapp.ui.watched_movies.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ritchie.movieapp.model.WatchedMovie;

public class WatchedMovieViewHolder extends RecyclerView.ViewHolder {

    private WatchedMoviesAdapter.WatchedMovieCallback callback;

    public WatchedMovieViewHolder(@NonNull View itemView, WatchedMoviesAdapter.WatchedMovieCallback callback) {
        super(itemView);
        this.callback = callback;
    }

    public void bind(WatchedMovie watchedMovie) {
    }
}
