package com.joaopaulosj.movies.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.joaopaulosj.movies.BaseApplication;
import com.joaopaulosj.movies.data.models.Movie;
import com.joaopaulosj.movies.data.models.MoviesResponse;
import com.joaopaulosj.movies.data.repository.MovieRepositoryImpl;

import javax.inject.Inject;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

public class MoviesViewModel extends AndroidViewModel {

    @Inject
    MovieRepositoryImpl mRepository;

    private String mQuery = "";

    public MoviesViewModel(Application application) {
        super(application);
        BaseApplication.get(application).getComponent().inject(this);
    }

    public void resetPopularMovies() {
        mRepository.loadPopularMovies(1);
    }

    public void loadMorePopularMovies(int page) {
        mRepository.loadPopularMovies(page);
    }

    public LiveData<MoviesResponse<Movie>> getPopularMovies() {
        return mRepository.initPopularMovies();
    }

    public void setQuery(String query){
        mQuery = query;
        resetSearchMovies();
    }

    public void resetSearchMovies() {
        mRepository.loadSearchMovies(mQuery, 1);
    }

    public void loadMoreSearchMovies(int page) {
        mRepository.loadSearchMovies(mQuery, page);
    }

    public LiveData<MoviesResponse<Movie>> getSearchMovies() {
        return mRepository.initSearchMovies();
    }
}
