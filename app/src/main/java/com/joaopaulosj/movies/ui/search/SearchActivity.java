package com.joaopaulosj.movies.ui.search;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.joaopaulosj.movies.R;
import com.joaopaulosj.movies.data.models.Movie;
import com.joaopaulosj.movies.data.models.MoviesResponse;
import com.joaopaulosj.movies.ui.SearchMovieAdapter;
import com.joaopaulosj.movies.ui.base.BaseActivity;
import com.joaopaulosj.movies.ui.main.MainViewModel;
import com.joaopaulosj.movies.ui.utils.EndlessRecyclerViewScrollListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.search_movies_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private MainViewModel mViewModel;
    private SearchMovieAdapter mAdapter;
    private EndlessRecyclerViewScrollListener mScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        displaySearchMovies();
        setRecyclerView();
        setSwipeRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.requestFocus();
        searchView.setOnQueryTextListener(this);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        hideSoftKeyboard();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!newText.isEmpty())
            searchMovie(newText);

        return false;
    }

    private void setSwipeRefresh(){
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mViewModel.resetSearchMovies();
            }
        });
    }

    private void searchMovie(String query) {
        mAdapter.clear();
        mViewModel.setQuery(query);
    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new SearchMovieAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mSwipeRefresh.setRefreshing(true);
                mViewModel.loadMoreSearchMovies(page);
            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);
    }

    private void displaySearchMovies() {
        mViewModel.getSearchMovies().observe(this, new Observer<MoviesResponse<Movie>>() {
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
