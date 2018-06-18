package com.example.android.mymovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.mymovie.adapter.OneCategoryShowAdapter;
import com.example.android.mymovie.pojo.MoviePojo;
import com.example.android.mymovie.pojo.Result;
import com.example.android.mymovie.sync.NetworkInfo;
import com.example.android.mymovie.sync.NetworkInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OneCategoryShowFragment extends Fragment {
    OneCategoryShowAdapter oneCategoryShowAdapter;
    public static OneCategoryShowFragment newInstance(String category){
        OneCategoryShowFragment oneCategoryShowFragment = new OneCategoryShowFragment();
        Bundle args = new Bundle();
        args.putString("category",category);
        oneCategoryShowFragment.setArguments(args);
        return oneCategoryShowFragment;
    }

    public OneCategoryShowFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        String category = getArguments()!=null?getArguments().getString("category"):null;
        View rootView = inflater.inflate(R.layout.fragment_one_category_show,container,false);
        RecyclerView categoryRecyclerView = rootView.findViewById(R.id.show_recyclerview);
        oneCategoryShowAdapter = new OneCategoryShowAdapter(getContext());
        /*listShowItems.add(new ListShowItem(R.drawable.ic_dashboard_black_24dp,"whatever",6.7,14218));
        oneCategoryShowAdapter.setListShowItems(listShowItems);*/
        //todo:adapter.setlistitems
        categoryRecyclerView.setAdapter(oneCategoryShowAdapter);
        loadJson(category);
        return rootView;
    }

    private void loadJson(String category){
        //todo: assign different uri based on category
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkInfo.DISCOVER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetworkInterface networkInterface = retrofit.create(NetworkInterface.class);
        Call<MoviePojo> moviePojoCall = networkInterface.getMovie(NetworkInfo.MOVIE_POPULAR_PATH,NetworkInfo.API_KEY,NetworkInfo.DISCOVER_PAGE_COUNT);
        moviePojoCall.enqueue(new Callback<MoviePojo>() {
            @Override
            public void onResponse(Call<MoviePojo> call, Response<MoviePojo> response) {
                if (response.isSuccessful()){
                    MoviePojo moviePojo = response.body();
                    if (moviePojo!= null && moviePojo.getResults()!=null){
                        List<Result> results = moviePojo.getResults();
                        ArrayList<Result> movies = new ArrayList<>();
                        for (Result result:results){
                            movies.add(result);
                        }
                        oneCategoryShowAdapter.setResults(movies);
                        //mostPopularPagerAdapter.setmListShowItems(listsOfMovies);
                    }else{
                        Log.i("Navigation fragment","There is something wrong");
                    }
                }else{
                    Log.i("Navigation fragment","response is not successful");
                }
            }

            @Override
            public void onFailure(Call<MoviePojo> call, Throwable t) {
                //todo
                Log.i("navigation fragment","failure in getting data from uri"+t);
            }
        });
    }

}
