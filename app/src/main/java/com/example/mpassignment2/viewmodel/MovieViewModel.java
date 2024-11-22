package com.example.mpassignment2.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mpassignment2.model.MovieDetails;
import com.example.mpassignment2.model.MovieSearchResult;
import com.example.mpassignment2.network.ApiClient;
import com.example.mpassignment2.network.ApiService;
import com.example.mpassignment2.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    /// The ApiService object to make network requests
    private final ApiService apiService;

    /// Live Data for search results, movie details, loading status, and error messages
    private final MutableLiveData<MovieSearchResult> searchResults = new MutableLiveData<>();
    private final MutableLiveData<MovieDetails> movieDetails = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();


    public MovieViewModel() {
        apiService = ApiClient.getApiService();
    }

    // LiveData for search results
    public LiveData<MovieSearchResult> getSearchResults() {
        return searchResults;
    }

    // LiveData for movie details
    public LiveData<MovieDetails> getMovieDetails() {
        return movieDetails;
    }

    // LiveData for loading status
    public LiveData<Boolean> getLoadingStatus() {
        return isLoading;
    }

    // LiveData for error messages
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    /**
     * Search for movies
     * Change the loading status to true
     * Make a network request to search for movies
     * If the response is successful and the response is "True", set the search results
     * Otherwise, set an error message Change the loading status to false
     *
     * @param query The search query
     */
    public void searchMovies(String query) {
        isLoading.setValue(true);
        apiService.searchMovies(query, Constants.API_KEY).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<MovieSearchResult> call, @NonNull Response<MovieSearchResult> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null && "True".equals(response.body().getResponse())) {
                    searchResults.setValue(response.body());
                } else {
                    errorMessage.setValue("No results found.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieSearchResult> call, @NonNull Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Failed to fetch data: " + t.getMessage());
            }
        });
    }

    /**
     * Fetch movie details
     * Change the loading status to true
     * Make a network request to fetch movie details
     * If the response is successful, set the movie details
     * Otherwise, set an error message Change the loading status to false
     *
     * @param imdbID The IMDB ID of the movie
     */
    public void fetchMovieDetails(String imdbID) {
        isLoading.setValue(true);
        apiService.getMovieDetails(imdbID, Constants.API_KEY).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    movieDetails.setValue(response.body());
                } else {
                    errorMessage.setValue("Failed to fetch movie details.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieDetails> call, @NonNull Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error: " + t.getMessage());
            }
        });
    }
}
