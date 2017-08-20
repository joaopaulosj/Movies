package com.joaopaulosj.movies.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

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
    private MutableLiveData<MoviesResponse<Movie>> mSearchMovies = new MutableLiveData<>();
    private Call searchMoviesCall;

    @Inject
    public MovieRepositoryImpl(MoviesService mService) {
        this.mService = mService;
    }

    @Override
    public void loadPopularMovies(int page) {
        mService.getPopularMovies(page).enqueue(new Callback<MoviesResponse<Movie>>() {
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

    public LiveData<MoviesResponse<Movie>> initPopularMovies() {
        return mPopularMovies;
    }

    @Override
    public void loadSearchMovies(final String search, int page) {
        if (searchMoviesCall != null) {
            searchMoviesCall.cancel();
            mSearchMovies.setValue(null);
        }

        searchMoviesCall = mService.searchMovie(search, page);
        searchMoviesCall.enqueue(new Callback<MoviesResponse<Movie>>() {
            @Override
            public void onResponse(Call<MoviesResponse<Movie>> call, Response<MoviesResponse<Movie>> response) {
                if (response.body() != null)
                    Log.v("MOV", "response for: " + search
                            + " of size " + response.body().getMovies().size());
                mSearchMovies.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MoviesResponse<Movie>> call, Throwable t) {
                if (!t.getMessage().equals("Canceled")) {
                    MoviesResponse<Movie> response = new MoviesResponse<>();
                    response.setError(t.getMessage());
                    mSearchMovies.setValue(response);
                }
            }
        });
    }

    public LiveData<MoviesResponse<Movie>> initSearchMovies() {
        return mSearchMovies;
    }

}
