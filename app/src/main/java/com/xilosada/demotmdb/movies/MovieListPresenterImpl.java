package com.xilosada.demotmdb.movies;

import android.util.Log;

import java.util.List;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xilosada on 27/11/16.
 */
public class MovieListPresenterImpl implements MoviesContract.MovieListPresenter {

    private MoviesContract.MovieListView movieListView;
    private MoviesProvider moviesProvider;
    private String query = "";
    private Disposable disposable;

    public MovieListPresenterImpl(MoviesContract.MovieListView movieListView, MoviesProvider moviesProvider) {
        this.movieListView = movieListView;
        this.moviesProvider = moviesProvider;
        requestPage(MoviesProvider.FIRST_PAGE);
    }

    @Override
    public void setQuery(String query) {
        movieListView.reset();
        this.query = query;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        requestPage(MoviesProvider.FIRST_PAGE);
    }

    @Override
    public void requestPage(int page) {
        if (query.isEmpty()) {
            disposable = loadFromPopular(page);
        } else {
            disposable = loadFromSearch(query, page);
        }
    }

    private Disposable loadFromPopular(int page) {
        return moviesProvider.getPopularMovies(page)
                .compose(renderMovies())
                .subscribe();
    }

    private Disposable loadFromSearch(String query, int page) {
        return moviesProvider.searchMovies(query, page)
                .compose(renderMovies())
                .subscribe();
    }

    private ObservableTransformer<List<Movie>, List<Movie>> renderMovies() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(movieListView::renderMovies)
                .doOnError(throwable -> Log.d("error", throwable.getMessage()));
    }

}
