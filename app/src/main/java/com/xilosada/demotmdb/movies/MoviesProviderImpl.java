package com.xilosada.demotmdb.movies;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tmdb.TMDBService;

/**
 * Created by xilosada on 27/11/16.
 */

public class MoviesProviderImpl implements MoviesProvider{

    private final TMDBService service;

    public MoviesProviderImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TMDBService.PRODUCTION_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.service = retrofit.create(TMDBService.class);
    }


    @Override
    public Observable<List<Movie>> getPopularMovies() {
         return service.getPopularMovies("93aea0c77bc168d8bbce3918cefefa45", "en-US")
                .map(moviePage -> moviePage.results)
                .toObservable();
    }

    @Override
    public Observable<List<Movie>> getPopularMovies(int page) {
        return service.getPopularMovies("93aea0c77bc168d8bbce3918cefefa45", "en-US", page)
                .map(moviePage -> moviePage.results)
                .toObservable();
    }

    @Override
    public Observable<List<Movie>> searchMovies(String query, int page) {
        return service.searchMovies("93aea0c77bc168d8bbce3918cefefa45", "en-US", page, query)
                .map(moviePage -> moviePage.results)
                .toObservable();
    }
}
