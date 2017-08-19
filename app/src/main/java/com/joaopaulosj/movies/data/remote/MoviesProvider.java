package com.joaopaulosj.movies.data.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.joaopaulosj.movies.NetConstants;
import com.joaopaulosj.movies.data.models.Movie;
import com.joaopaulosj.movies.data.models.MoviesResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jpsja_000 on 18/08/2017.
 */

public class MoviesProvider {

    private MoviesService mService;

    @Inject
    public MoviesProvider(MoviesService mService) {
        this.mService = mService;
    }

    public LiveData<MoviesResponse<Movie>> getPopularMovies(int page){
        final MutableLiveData<MoviesResponse<Movie>> result = new MutableLiveData<>();

        mService.getPopularMovies(NetConstants.API_KEY, page).enqueue(new Callback<MoviesResponse<Movie>>() {
            @Override
            public void onResponse(Call<MoviesResponse<Movie>> call, Response<MoviesResponse<Movie>> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MoviesResponse<Movie>> call, Throwable t) {
                MoviesResponse<Movie> response = new MoviesResponse<>();
                response.setError(t.getMessage());
                result.setValue(response);
            }
        });

        return result;
    }

    public LiveData<MoviesResponse<Movie>> searchMovie (String search, int page){
        mService.searchMovie(NetConstants.API_KEY, search, page);
        return new MutableLiveData<>();
    }

}
