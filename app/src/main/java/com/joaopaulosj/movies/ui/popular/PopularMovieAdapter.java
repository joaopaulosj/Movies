package com.joaopaulosj.movies.ui.popular;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joaopaulosj.movies.R;
import com.joaopaulosj.movies.data.models.Movie;
import com.joaopaulosj.movies.ui.utils.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

public class PopularMovieAdapter extends BaseRecyclerViewAdapter{

    private List<Movie> mList = new ArrayList<>();
    private Activity mActivity;

    public PopularMovieAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public int getDisplayableItemsCount() {
        return mList.size();
    }

    @Override
    public void onBindRecyclerViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PopularMovieViewHolder) ((PopularMovieViewHolder) holder).bind(position);
    }

    @Override
    protected RecyclerView.ViewHolder getItemViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_movie, parent, false);
        return new PopularMovieViewHolder(view);
    }

    public void addItems(List<Movie> list) {
        mList.addAll(list);
        notifyDataChanged();
    }

    public void clear() {
        mList.clear();
    }

    class PopularMovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_movie_poster_iv)
        ImageView moviePosterIv;
        @BindView(R.id.item_movie_title_tv)
        TextView movieTitleTv;

        public PopularMovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position){
            Movie movie = mList.get(position);

            String pos = "#" + (position + 1);
            movieTitleTv.setText(pos + " - " + movie.getTitle());
            Glide.with(mActivity).load(movie.getPosterUrl()).placeholder(R.color.colorAccent).into(moviePosterIv);
        }
    }
}
