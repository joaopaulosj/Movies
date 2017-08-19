package com.joaopaulosj.movies.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.joaopaulosj.movies.R;
import com.joaopaulosj.movies.data.models.Movie;
import com.joaopaulosj.movies.data.models.MoviesResponse;
import com.joaopaulosj.movies.ui.MovieAdapter;
import com.joaopaulosj.movies.ui.base.BaseActivity;
import com.joaopaulosj.movies.ui.utils.EndlessRecyclerViewScrollListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.popular_movies_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private MainViewModel mViewModel;
    private MovieAdapter mAdapter;
    private EndlessRecyclerViewScrollListener mScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        applicationComponent().inject(this);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        setActionBar(getString(R.string.app_name));
        setRecyclerView();
        displayPopularMovies();
        setSwipeRefreshLayout();
    }

    private void setSwipeRefreshLayout() {
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.clear();
                mViewModel.resetPopularMovies();
            }
        });
    }

    private void setRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mSwipeRefresh.setRefreshing(true);
                mViewModel.loadMorePopularMovies(page);
            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);
    }

    void displayPopularMovies() {
        mSwipeRefresh.setRefreshing(true);
        mViewModel.getPopularMovies().observe(this, new Observer<MoviesResponse<Movie>>() {
            @Override
            public void onChanged(@Nullable MoviesResponse<Movie> moviesResponse) {
                mSwipeRefresh.setRefreshing(false);
                if (moviesResponse != null) {
                    if (moviesResponse.getError() != null)
                        showToast(moviesResponse.getError());
                    else if (moviesResponse.getMovies().size() > 0)
                        mAdapter.addItems(moviesResponse.getMovies());
                }
            }
        });
    }
}
