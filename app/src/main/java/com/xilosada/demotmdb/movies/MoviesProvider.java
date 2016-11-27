package com.xilosada.demotmdb.movies;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xilosada on 27/11/16.
 */

public interface MoviesProvider {

    int FIRST_PAGE = 1;

    Observable<List<Movie>> getPopularMovies(int page);

    Observable<List<Movie>> searchMovies(String query, int page);
}
