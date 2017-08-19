package com.joaopaulosj.movies.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

public class MoviesResponse<T> {

    @SerializedName("results")
    private List<T> movies;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("page")
    private int page;

    private String error;

    public List<T> getMovies() {
        return movies;
    }

    public void setMovies(List<T> movies) {
        this.movies = movies;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
