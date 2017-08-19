package com.joaopaulosj.movies.data.remote;

import android.arch.lifecycle.LiveData;

import com.joaopaulosj.movies.data.models.Movie;
import com.joaopaulosj.movies.data.models.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jpsja_000 on 18/08/2017.
 */

public interface MoviesService {

    @GET("movie/popular")
    Call<MoviesResponse<Movie>> getPopularMovies(@Query ("api_key") String apiKey, @Query("page") int page);

    @GET("search/movie")
    Call<MoviesResponse<Movie>> searchMovie(@Query ("api_key") String apiKey, @Query("query")String search, @Query("page") int page);

}
