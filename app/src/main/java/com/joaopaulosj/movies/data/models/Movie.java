package com.joaopaulosj.movies.data.models;

import com.google.gson.annotations.SerializedName;
import com.joaopaulosj.movies.Constants;
import com.joaopaulosj.movies.NetConstants;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

public class Movie {

    @SerializedName("id")
    private String id = "";

    @SerializedName("title")
    private String title = "";

    @SerializedName("overview")
    private String overview = "";

    @SerializedName("release_date")
    private String release = "";

    @SerializedName("poster_path")
    private String poster_url = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getPosterUrl() {
        return NetConstants.BASE_POSTER_URL + poster_url;
    }

    public String getYear() {
        if (!release.isEmpty())
            return release.substring(0, 4);
        else
            return release;
    }

}
