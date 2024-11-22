package com.example.mpassignment2.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.mpassignment2.R;
import com.example.mpassignment2.databinding.ActivityMovieDetailsBinding;
import com.example.mpassignment2.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailsBinding binding;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ViewBinding
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        // Get IMDb ID from intent
        String imdbID = getIntent().getStringExtra("MOVIE_ID");
        if (imdbID == null || imdbID.isEmpty()) {
            Toast.makeText(this, "Movie ID is missing!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Fetch movie details
        viewModel.fetchMovieDetails(imdbID);

        // Observe ViewModel
        observeViewModel();

        // Back button listener
        binding.btnBack.setOnClickListener(v -> finish());
    }

    /**
     * Observe ViewModel for changes
     */
    private void observeViewModel() {

        /// Observe Movie Details and update UI accordingly
        viewModel.getMovieDetails().observe(this, movie -> {

            if (movie == null) {
                Toast.makeText(this, "Movie not found!", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            binding.tvTitle.setText(movie.getTitle());
            binding.tvYear.setText(movie.getYear());
            binding.tvGenre.setText(movie.getGenre());
            binding.tvDirector.setText(movie.getDirector());
            binding.tvActors.setText(movie.getActors());
            binding.tvPlot.setText(movie.getPlot());
            binding.tvRating.setText(String.format("IMDb Rating: %s", movie.getImdbRating()));
            binding.tvRuntime.setText(movie.getRuntime());
            binding.tvBoxOffice.setText(movie.getBoxOffice());

            Glide.with(this)
                    .load(movie.getPoster())
                    .placeholder(R.drawable.placeholder_image)
                    .into(binding.ivPoster);
        });

        /// Observe Loading Status and show/hide progress bar accordingly
        /// Also show/hide details scroll view
        /// This is done to provide better user experience
        viewModel.getLoadingStatus().observe(this, isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.detailsScrollView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        });

        /// Observe Error Message and show toast message
        viewModel.getErrorMessage().observe(this, message -> {
            if (message != null) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
