package com.joaopaulosj.movies.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.joaopaulosj.movies.NetConstants;
import com.joaopaulosj.movies.data.models.Movie;
import com.joaopaulosj.movies.data.models.MoviesResponse;
import com.joaopaulosj.movies.data.remote.MoviesProvider;
import com.joaopaulosj.movies.data.remote.MoviesService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jpsja_000 on 19/08/2017.
 */
@Singleton
public class MovieRepositoryImpl implements MovieRepository {

    private MoviesProvider mProvider;

    @Inject
    public MovieRepositoryImpl(MoviesProvider mProvider) {
        this.mProvider = mProvider;
    }

    @Override
    public LiveData<MoviesResponse<Movie>> getPopularMovies(int page){
        return mProvider.getPopularMovies(page);
    }

    @Override
    public LiveData<MoviesResponse<Movie>> searchMovie(String search, int page){
        return mProvider.searchMovie(search, page);
    }

}
