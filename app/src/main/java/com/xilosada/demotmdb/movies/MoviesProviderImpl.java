package com.xilosada.demotmdb.movies;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xilosada.demotmdb.DeviceInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tmdb.TMDBService;

/**
 * Created by xilosada on 27/11/16.
 */

public class MoviesProviderImpl implements MoviesProvider {

    public static final String API_KEY = "YOUR_TMDB_API_KEY";
    private final TMDBService service;
    private DeviceInfo deviceInfo;

    public MoviesProviderImpl(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TMDBService.PRODUCTION_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.service = retrofit.create(TMDBService.class);
    }

    @Override
    public Observable<List<Movie>> getPopularMovies(int page) {
        return service.getPopularMovies(API_KEY, deviceInfo.getLocale(), page)
                .map(moviePage -> moviePage.results)
                .toObservable();
    }

    @Override
    public Observable<List<Movie>> searchMovies(String query, int page) {
        return service.searchMovies(API_KEY, deviceInfo.getLocale(), page, query)
                .map(moviePage -> moviePage.results)
                .toObservable();
    }
}
