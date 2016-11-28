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
    String API_KEY = "api_key";
    String LANGUAGE = "language";
    String PAGE = "page";
    String QUERY = "query";

    @GET("movie/popular")
    Flowable<Page<Movie>> getPopularMovies(
            @Query(API_KEY) String apiKey,
            @Query(LANGUAGE) String language,
            @Query(PAGE) int page);

    @GET("search/movie")
    Flowable<Page<Movie>> searchMovies(
            @Query(API_KEY) String apiKey,
            @Query(LANGUAGE) String language,
            @Query(PAGE) int page,
            @Query(QUERY) String query);

}
