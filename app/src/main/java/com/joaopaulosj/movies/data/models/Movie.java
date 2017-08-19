package com.joaopaulosj.movies.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

public class Movie {

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("overview")
    public String overview;

    @SerializedName("release_date")
    public String release;

    @SerializedName("poster_path")
    public String poster;

}
