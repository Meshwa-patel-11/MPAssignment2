package com.example.mpassignment2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mpassignment2.R;
import com.example.mpassignment2.databinding.MovieItemBinding;
import com.example.mpassignment2.model.MovieSearchResult;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final List<MovieSearchResult.Movie> movies;
    private final OnItemClickListener listener;

    /**
     * Interface to handle item click events
     * Created because we want to handle item click events in the activity
     */
    public interface OnItemClickListener {
        void onItemClick(MovieSearchResult.Movie movie);
    }

    /**
     * Constructor
     *
     * @param movies   List of movies to display
     * @param listener OnItemClickListener object
     */
    public MovieAdapter(List<MovieSearchResult.Movie> movies, OnItemClickListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieSearchResult.Movie movie = movies.get(position);
        holder.bind(movie, listener);
    }

    /**
     * Update the list of movies displayed
     *
     * @param newMovies List of movies to update
     */
    public void updateMovies(List<MovieSearchResult.Movie> newMovies) {
        movies.clear();
        movies.addAll(newMovies);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    /**
     * ViewHolder class for the RecyclerView
     */
    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        MovieItemBinding binding;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = MovieItemBinding.bind(itemView);
        }

        public void bind(MovieSearchResult.Movie movie, OnItemClickListener listener) {
            binding.tvTitle.setText(movie.getTitle());
            binding.tvYear.setText(movie.getYear());
            binding.tvType.setText(movie.getType());

            // Load poster image using Glide
            // I have used placeholder image in case the poster is not available
            Glide.with(itemView.getContext())
                    .load(movie.getPoster())
                    .placeholder(R.drawable.placeholder_image)
                    .into(binding.ivPoster);

            // Handle item click event using the listener created in the adapter
            itemView.setOnClickListener(v -> listener.onItemClick(movie));
        }
    }
}
