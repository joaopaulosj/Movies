package com.joaopaulosj.movies.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.joaopaulosj.movies.data.models.Movie;
import com.joaopaulosj.movies.data.models.MoviesResponse;
import com.joaopaulosj.movies.data.repository.MovieRepository;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

public class MainViewModel extends AndroidViewModel {

    MovieRepository mRepository;

    private LiveData<MoviesResponse<Movie>> mPopularMovies = new MutableLiveData<>();
    private LiveData<MoviesResponse<Movie>> mSearchMovies = new MutableLiveData<>();

    public MainViewModel(Application application) {
        super(application);
    }

    public LiveData<MoviesResponse<Movie>> getPopularMovies(int page){
            mPopularMovies = mRepository.getPopularMovies(page);

            return mPopularMovies;
    }
}
