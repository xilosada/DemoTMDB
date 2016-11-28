package com.xilosada.demotmdb.movies;

import java.util.List;

/**
 * Created by xilosada on 27/11/16.
 */

public interface MoviesContract {

    interface MovieListView {

        void renderMovies(List<Movie> movies);

        void reset();
    }

    interface MovieListPresenter {

        void requestPage(int page);

        void setQuery(String query);

    }
}
