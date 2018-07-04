package com.example.android.mymovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NavigationFragment extends Fragment {
    private String TAG = NavigationFragment.class.getName();
    public static NavigationFragment newInstance(String title){
        NavigationFragment navigationFragment = new NavigationFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        navigationFragment.setArguments(args);
        return navigationFragment;
    }

    public NavigationFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        String title = getArguments()!=null?getArguments().getString("title"):null;
        View rootView = inflater.inflate(R.layout.fragment_navigation,container,false);
        OneCategoryShowFragment mostPopularFragment = OneCategoryShowFragment.newInstance(title,getString(R.string.most_popular_label));
        OneCategoryShowFragment topRatedFragment = OneCategoryShowFragment.newInstance(title,getString(R.string.top_rated_label));
        OneCategoryShowFragment upcomingFragment = OneCategoryShowFragment.newInstance(title,getString(R.string.upcoming_label));
        OneCategoryShowFragment nowPlayFragment = OneCategoryShowFragment.newInstance(title,getString(R.string.now_playing_label));
        FragmentTransaction mostPopularTransaction = getChildFragmentManager().beginTransaction();
        FragmentTransaction nowPlayTransaction = getChildFragmentManager().beginTransaction();
        FragmentTransaction upcomingTransaction = getChildFragmentManager().beginTransaction();
        FragmentTransaction topRatedTransaction = getChildFragmentManager().beginTransaction();
        mostPopularTransaction.add(R.id.most_popular_container,mostPopularFragment);
        Log.i(TAG,"most popular fragment is added");
        nowPlayTransaction.add(R.id.now_playing_container,nowPlayFragment);
        Log.i(TAG,"now playing fragment is added");
        upcomingTransaction.add(R.id.upcoming_container,upcomingFragment);
        Log.i(TAG,"upcoming fragment is added");
        topRatedTransaction.add(R.id.top_rated_container,topRatedFragment);
        Log.i(TAG,"top rated fragment is added");
        mostPopularTransaction.commit();
        nowPlayTransaction.commit();
        upcomingTransaction.commit();
        topRatedTransaction.commit();

        return rootView;
    }

}
