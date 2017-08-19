package com.joaopaulosj.movies.data.repository;

import android.arch.lifecycle.LiveData;

import com.joaopaulosj.movies.data.models.Movie;
import com.joaopaulosj.movies.data.models.MoviesResponse;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

public interface MovieRepository {

    public LiveData<MoviesResponse<Movie>> getPopularMovies(int page);

    public LiveData<MoviesResponse<Movie>> searchMovie(String search, int page);

}
