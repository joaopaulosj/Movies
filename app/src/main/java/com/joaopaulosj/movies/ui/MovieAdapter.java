package com.joaopaulosj.movies.ui;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
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

public class MovieAdapter extends BaseRecyclerViewAdapter{

    private List<Movie> mList = new ArrayList<>();
    private Activity mActivity;

    public MovieAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void addItems(List<Movie> list) {
        mList.addAll(list);
        notifyDataChanged();
    }

    public void clear() {
        mList.clear();
        notifyDataChanged();
    }

    public void setList(List<Movie> list) {
        mList = list;
        notifyDataChanged();
    }

    @Override
    public int getDisplayableItemsCount() {
        return mList.size();
    }

    @Override
    public void onBindRecyclerViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder) ((MovieViewHolder) holder).bind(position);
    }

    @Override
    protected RecyclerView.ViewHolder getItemViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_movie, parent, false);
        return new MovieViewHolder(view);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_movie_poster_iv)
        ImageView moviePosterIv;
        @BindView(R.id.item_movie_title_tv)
        TextView movieTitleTv;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position){
            Movie movie = mList.get(position);

            movieTitleTv.setText(movie.title);
            Glide.with(mActivity).load(movie.getPosterUrl()).placeholder(R.color.colorAccent).into(moviePosterIv);
        }
    }
}
