package com.joaopaulosj.movies.data.repository;

import android.arch.lifecycle.LiveData;

import com.joaopaulosj.movies.data.models.Movie;
import com.joaopaulosj.movies.data.models.MoviesResponse;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

public interface MovieRepository {

    void loadPopularMovies(int page);

    void loadSearchMovies(String search, int page);

}
