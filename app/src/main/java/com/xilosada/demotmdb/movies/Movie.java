package com.xilosada.demotmdb.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xilosada on 27/11/16.
 */

public class Movie {

    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("adult")
    @Expose
    public Boolean adult;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("release_date")
    @Expose
    public String releaseDate;
    @SerializedName("genre_ids")
    @Expose
    public List<Long> genreIds = new ArrayList<Long>();
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("original_title")
    @Expose
    public String originalTitle;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("popularity")
    @Expose
    public Double popularity;
    @SerializedName("vote_count")
    @Expose
    public Long voteCount;
    @SerializedName("video")
    @Expose
    public Boolean video;
    @SerializedName("vote_average")
    @Expose
    public Double voteAverage;

    public Movie withPosterPath(String posterPath) {
        this.posterPath = posterPath;
        return this;
    }

    public Movie withAdult(Boolean adult) {
        this.adult = adult;
        return this;
    }

    public Movie withOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public Movie withReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Movie withGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
        return this;
    }

    public Movie withId(Long id) {
        this.id = id;
        return this;
    }

    public Movie withOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        return this;
    }

    public Movie withOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
        return this;
    }

    public Movie withTitle(String title) {
        this.title = title;
        return this;
    }

    public Movie withBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
        return this;
    }

    public Movie withPopularity(Double popularity) {
        this.popularity = popularity;
        return this;
    }

    public Movie withVoteCount(Long voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    public Movie withVideo(Boolean video) {
        this.video = video;
        return this;
    }

    public Movie withVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
        return this;
    }
}
