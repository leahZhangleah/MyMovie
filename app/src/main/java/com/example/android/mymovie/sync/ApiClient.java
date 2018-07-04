package com.example.android.mymovie.sync;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    Retrofit retrofit;
    private static final Object LOCK = new Object();

    public Retrofit getRetrofit() {
        if (retrofit==null){
            synchronized (LOCK){
                retrofit = new Retrofit.Builder()
                        .baseUrl(NetworkInfo.DISCOVER_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }
        return retrofit;
    }
}
