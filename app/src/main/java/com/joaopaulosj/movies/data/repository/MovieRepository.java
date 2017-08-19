package com.joaopaulosj.movies.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.joaopaulosj.movies.data.models.Movie;
import com.joaopaulosj.movies.data.models.MoviesResponse;

/**
 * Created by jpsja_000 on 18/08/2017.
 */

public class MovieRepository {

    public LiveData<MoviesResponse<Movie>> getPopularMovies(int page){
        return new MutableLiveData<>();
    }

    public LiveData<MoviesResponse<Movie>> searchMovies(String search, int page){
        return new MutableLiveData<>();
    }

}
