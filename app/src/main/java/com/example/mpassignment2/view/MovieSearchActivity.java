package com.example.mpassignment2.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mpassignment2.adapter.MovieAdapter;
import com.example.mpassignment2.databinding.ActivityMovieSearchBinding;
import com.example.mpassignment2.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class MovieSearchActivity extends AppCompatActivity {

    private ActivityMovieSearchBinding binding;
    private MovieAdapter adapter;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use view binding
        binding = ActivityMovieSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get the view model
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        // Set up the recycler view
        adapter = new MovieAdapter(new ArrayList<>(), movie -> {
            Intent intent = new Intent(MovieSearchActivity.this, MovieDetailsActivity.class);
            intent.putExtra("MOVIE_ID", movie.getImdbID());
            startActivity(intent);
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        // Observe the view model
        observeViewModel();

        // Set up the search button
        binding.btnSearch.setOnClickListener(v -> {
            String query = binding.etSearch.getText().toString().trim();
            if (query.isEmpty()) {
                Toast.makeText(this, "Please enter a movie name", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.searchMovies(query);
            }
        });
    }

    /**
     * Observe the view model
     */
    private void observeViewModel() {
        /// Observe the search results and update the adapter
        viewModel.getSearchResults().observe(this, searchResult -> {
            adapter.updateMovies(searchResult.getMovies());

            if (!searchResult.getMovies().isEmpty()) {
                binding.recyclerView.setVisibility(android.view.View.VISIBLE);
            }
        });

        /// Observe the loading status and show/hide the progress bar
        viewModel.getLoadingStatus().observe(this, isLoading -> {
            binding.progressBar.setVisibility(isLoading ? android.view.View.VISIBLE : android.view.View.GONE);
        });

        /// Observe the error message and show a toast
        viewModel.getErrorMessage().observe(this, message -> {
            if (message != null) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
