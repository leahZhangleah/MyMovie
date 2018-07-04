package com.example.android.mymovie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MoviePojo {

    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<>();

    public Integer getPage() {
        return page;
    }

    public List<Result> getResults() {
        return results;
    }


}

