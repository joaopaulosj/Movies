package com.joaopaulosj.movies.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.joaopaulosj.movies.R;
import com.joaopaulosj.movies.data.models.Movie;
import com.joaopaulosj.movies.data.models.MoviesResponse;
import com.joaopaulosj.movies.ui.MovieAdapter;
import com.joaopaulosj.movies.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private MainViewModel mViewModel;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        setAdapter();

        displayPopularMovies();
    }

    void setAdapter(){

    }

    void displayPopularMovies(){
        mViewModel.getPopularMovies(1).observe(this, new Observer<MoviesResponse<Movie>>() {
            @Override
            public void onChanged(@Nullable MoviesResponse<Movie> movieMoviesResponse) {

            }
        });
    }
}
