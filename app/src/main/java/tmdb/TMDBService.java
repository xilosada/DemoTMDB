package tmdb;


import com.xilosada.demotmdb.movies.Movie;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by xilosada on 27/11/16.
 */

public interface TMDBService {

    String PRODUCTION_ENDPOINT = "https://api.themoviedb.org/3/";

    @GET("movie/popular")
    Flowable<Page<Movie>> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("movie/popular")
    Flowable<Page<Movie>> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page);

    @GET("search/movie")
    Flowable<Page<Movie>> searchMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page,
            @Query("query") String query);

}
