package com.example.android.mymovie.sync;

import com.example.android.mymovie.pojo.MoviePojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkInterface {
    @GET("movie/{category}")
    Call<MoviePojo> getMovie(@Path("category") String category,
                             @Query("api_key") String api_key,
                             @Query("page") int page);
}
