package com.example.android.mymovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.mymovie.adapter.OneCategoryShowAdapter;
import com.example.android.mymovie.pojo.MoviePojo;
import com.example.android.mymovie.pojo.Result;
import com.example.android.mymovie.sync.ApiClient;
import com.example.android.mymovie.sync.NetworkInfo;
import com.example.android.mymovie.sync.NetworkInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OneCategoryShowFragment extends Fragment {
    private String TAG = OneCategoryShowFragment.class.getName();
    private OneCategoryShowAdapter oneCategoryShowAdapter;
    private String movieCategory, tvCategory;
    private Call<MoviePojo> moviePojoCall;
    public static OneCategoryShowFragment newInstance(String title, String category){
        OneCategoryShowFragment oneCategoryShowFragment = new OneCategoryShowFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putString("category",category);
        oneCategoryShowFragment.setArguments(args);
        return oneCategoryShowFragment;
    }

    public OneCategoryShowFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        String title = getArguments()!=null? getArguments().getString("title"):null;
        String category = getArguments()!=null?getArguments().getString("category"):null;
        View rootView = inflater.inflate(R.layout.fragment_one_category_show,container,false);
        TextView showLabel = rootView.findViewById(R.id.show_label);
        String label = checkTitleForShowLabel(title,category);
        showLabel.setText(label);

        RecyclerView categoryRecyclerView = rootView.findViewById(R.id.show_recyclerview);
        oneCategoryShowAdapter = new OneCategoryShowAdapter(getContext());
        categoryRecyclerView.setAdapter(oneCategoryShowAdapter);
        Log.i(TAG,"one category show fragment is created.");
        loadJson(title,category);

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        moviePojoCall.cancel();
    }


    private void loadJson(String title, String category){
        ApiClient client = new ApiClient();
        Retrofit retrofit = client.getRetrofit();
        NetworkInterface networkInterface = retrofit.create(NetworkInterface.class);
        String requiredCategory = checkTitleForCategory(title,category);
        if (title.equals(getString(R.string.title_movie))){
            moviePojoCall = networkInterface.getMovie(requiredCategory,NetworkInfo.API_KEY,NetworkInfo.DISCOVER_PAGE_COUNT);
            Log.i(TAG,"movie retrofit call is created");
        }else if(title.equals(getString(R.string.title_tv))){
            moviePojoCall = networkInterface.getTvShow(requiredCategory,NetworkInfo.API_KEY,NetworkInfo.DISCOVER_PAGE_COUNT);
            Log.i(TAG,"tv retrofit call is created");
        }
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
                        Log.i(TAG,"retrofit result is add to the adapter");
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

    private String checkTitleForShowLabel(String title, String category){
        String label = "";
        if (title !=null && category!=null){
            if (title.equals(getString(R.string.title_movie))){
                label = category;
            }else if(title.equals(getString(R.string.title_tv))){
                if(category.equals(getString(R.string.now_playing_label))){
                    label = getString(R.string.tv_airing_today);
                }else if (category.equals(getString(R.string.upcoming_label))){
                    label = getString(R.string.tv_on_air);
                }else if (category.equals(getString(R.string.top_rated_label))|| category.equals(getString(R.string.most_popular_label))){
                    label = category;
                }
            }
        }
        return label;
    }
    private String checkTitleForCategory(String title, String category){
        String requiredCategory = "";
        if (title !=null && category!=null){
            if (title.equals(getString(R.string.title_movie))){
                if(category.equals(getString(R.string.now_playing_label))){
                    requiredCategory = NetworkInfo.MOVIE_NOW_PLAYING_PATH;
                }else if (category.equals(getString(R.string.upcoming_label))){
                    requiredCategory = NetworkInfo.MOVIE_UPCOMING_PATH;
                }else if (category.equals(getString(R.string.top_rated_label))){
                    requiredCategory = NetworkInfo.MOVIE_TOP_RATED_PATH;
                }else if(category.equals(getString(R.string.most_popular_label))){
                    requiredCategory = NetworkInfo.MOVIE_POPULAR_PATH;
                }
            }else if(title.equals(getString(R.string.title_tv))){
                if(category.equals(getString(R.string.now_playing_label))){
                    requiredCategory = NetworkInfo.TV_AIRING_TODAY_PATH;
                }else if (category.equals(getString(R.string.upcoming_label))){
                    requiredCategory = NetworkInfo.TV_ON_THE_AIR_PATH;
                }else if (category.equals(getString(R.string.top_rated_label))){
                    requiredCategory = NetworkInfo.TV_TOP_RATED_PATH;
                }else if(category.equals(getString(R.string.most_popular_label))){
                    requiredCategory = NetworkInfo.TV_POPULAR_PATH;
                }
            }
        }
        return requiredCategory;
    }

}
