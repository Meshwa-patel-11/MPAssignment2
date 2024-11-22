package com.example.mpassignment2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchResult {
    @SerializedName("Search")
    private List<Movie> movies;

    @SerializedName("Response")
    private String response;

    public List<Movie> getMovies() {
        return movies;
    }


    public String getResponse() {
        return response;
    }


    public static class Movie {
        @SerializedName("Title")
        private String title;

        @SerializedName("Year")
        private String year;

        @SerializedName("imdbID")
        private String imdbID;

        @SerializedName("Type")
        private String type;

        @SerializedName("Poster")
        private String poster;

        public String getTitle() {
            return title;
        }


        public String getYear() {
            return year;
        }


        public String getImdbID() {
            return imdbID;
        }


        public String getType() {
            return type;
        }


        public String getPoster() {
            return poster;
        }

    }
}
