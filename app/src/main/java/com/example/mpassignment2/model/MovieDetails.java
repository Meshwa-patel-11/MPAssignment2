package com.example.mpassignment2.model;

import com.google.gson.annotations.SerializedName;

public class MovieDetails {
    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("Runtime")
    private String runtime;

    @SerializedName("Genre")
    private String genre;

    @SerializedName("Director")
    private String director;

    @SerializedName("Actors")
    private String actors;

    @SerializedName("Plot")
    private String plot;

    @SerializedName("Poster")
    private String poster;

    @SerializedName("imdbRating")
    private String imdbRating;

    @SerializedName("BoxOffice")
    private String boxOffice;

    public String getTitle() {
        return title;
    }


    public String getYear() {
        return year;
    }


    public String getRuntime() {
        return runtime;
    }


    public String getGenre() {
        return genre;
    }


    public String getDirector() {
        return director;
    }


    public String getActors() {
        return actors;
    }


    public String getPlot() {
        return plot;
    }

    public String getPoster() {
        return poster;
    }


    public String getImdbRating() {
        return imdbRating;
    }


    public String getBoxOffice() {
        return boxOffice;
    }
}
