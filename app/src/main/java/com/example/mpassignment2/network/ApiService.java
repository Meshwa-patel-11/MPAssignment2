package com.example.mpassignment2.network;

import com.example.mpassignment2.model.MovieDetails;
import com.example.mpassignment2.model.MovieSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface to define the API endpoints
 */
public interface ApiService {

    @GET("/")
    Call<MovieSearchResult> searchMovies(@Query("s") String query, @Query("apikey") String apiKey);

    @GET("/")
    Call<MovieDetails> getMovieDetails(@Query("i") String imdbID, @Query("apikey") String apiKey);
}
