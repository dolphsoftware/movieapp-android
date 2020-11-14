package com.ritchie.movieapp.ui.watched_movies.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ritchie.movieapp.R;
import com.ritchie.movieapp.model.WatchedMovie;

import java.util.List;

public class WatchedMoviesAdapter extends RecyclerView.Adapter<WatchedMovieViewHolder> {
    private final List<WatchedMovie> watchedMovieList;
    private final WatchedMovieCallback callback;

    public WatchedMoviesAdapter(List<WatchedMovie> watchedMovieList, WatchedMovieCallback callback) {
        this.watchedMovieList = watchedMovieList;
        this.callback = callback;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public WatchedMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WatchedMovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.li_watched_movie, null, false), callback);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchedMovieViewHolder holder, int position) {
        holder.bind(watchedMovieList.get(position));
    }

    @Override
    public int getItemCount() {
        return watchedMovieList.size();
    }

    public interface WatchedMovieCallback {
        void onMovieClicked(WatchedMovie movie);
    }
}
