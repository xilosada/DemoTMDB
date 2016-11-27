package com.xilosada.demotmdb.movies;

import java.util.List;

/**
 * Created by xilosada on 27/11/16.
 */

public class MoviesContract {

    public interface MovieListView {

        void addMovies(List<Movie> movies);

        void reset();
    }

    public interface MovieListPresenter {

        void requestPage(int page);

        void setQuery(String query);

    }
}
