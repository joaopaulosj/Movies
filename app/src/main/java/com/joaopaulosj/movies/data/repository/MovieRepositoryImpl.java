package com.joaopaulosj.movies.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.joaopaulosj.movies.NetConstants;
import com.joaopaulosj.movies.data.models.Movie;
import com.joaopaulosj.movies.data.models.MoviesResponse;
import com.joaopaulosj.movies.data.remote.MoviesService;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jpsja_000 on 19/08/2017.
 */
@Singleton
public class MovieRepositoryImpl implements MovieRepository {

    private MoviesService mService;
    private MutableLiveData<MoviesResponse<Movie>> mPopularMovies = new MutableLiveData<>();

    @Inject
    public MovieRepositoryImpl(MoviesService mService) {
        this.mService = mService;
    }

    @Override
    public void loadPopularMovies(int page) {
        mService.getPopularMovies(NetConstants.API_KEY, page).enqueue(new Callback<MoviesResponse<Movie>>() {
            @Override
            public void onResponse(Call<MoviesResponse<Movie>> call, Response<MoviesResponse<Movie>> response) {
                mPopularMovies.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MoviesResponse<Movie>> call, Throwable t) {
                MoviesResponse<Movie> response = new MoviesResponse<>();
                response.setError(t.getMessage());
                mPopularMovies.setValue(response);
            }
        });
    }

    public LiveData<MoviesResponse<Movie>> getPopularMovies() {
        return mPopularMovies;
    }

    public LiveData<MoviesResponse<Movie>> searchMovie(String search, int page) {
        mService.searchMovie(NetConstants.API_KEY, search, page);
        return new MutableLiveData<>();
    }

}
