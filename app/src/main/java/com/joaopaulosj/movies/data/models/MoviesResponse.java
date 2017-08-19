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

}
