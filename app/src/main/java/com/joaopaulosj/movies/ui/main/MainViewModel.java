package com.joaopaulosj.movies.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.joaopaulosj.movies.BaseApplication;
import com.joaopaulosj.movies.data.models.Movie;
import com.joaopaulosj.movies.data.models.MoviesResponse;
import com.joaopaulosj.movies.data.repository.MovieRepository;
import com.joaopaulosj.movies.di.component.ApplicationComponent;

import javax.inject.Inject;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

public class MainViewModel extends AndroidViewModel {

    @Inject
    MovieRepository mRepository;

    private LiveData<MoviesResponse<Movie>> mPopularMovies = new MutableLiveData<>();
    private LiveData<MoviesResponse<Movie>> mSearchMovies = new MutableLiveData<>();

    public MainViewModel(Application application) {
        super(application);
        BaseApplication.get(application).getComponent().inject(this);
    }

    public LiveData<MoviesResponse<Movie>> getPopularMovies(int page){
            mPopularMovies = mRepository.getPopularMovies(page);

            return mPopularMovies;
    }
}
