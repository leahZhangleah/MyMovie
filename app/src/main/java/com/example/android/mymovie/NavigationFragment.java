package com.example.android.mymovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NavigationFragment extends Fragment {
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
        OneCategoryShowFragment mostPopularFragment = OneCategoryShowFragment.newInstance(getString(R.string.most_popular_label));
        OneCategoryShowFragment nowPlayFragment = OneCategoryShowFragment.newInstance(getString(R.string.now_playing_label));
        OneCategoryShowFragment upcomingFragment = OneCategoryShowFragment.newInstance(getString(R.string.upcoming_label));
        OneCategoryShowFragment topRatedFragment = OneCategoryShowFragment.newInstance(getString(R.string.top_rated_label));
        FragmentTransaction mostPopularTransaction = getFragmentManager().beginTransaction();
        FragmentTransaction nowPlayTransaction = getFragmentManager().beginTransaction();
        FragmentTransaction upcomingTransaction = getFragmentManager().beginTransaction();
        FragmentTransaction topRatedTransaction = getFragmentManager().beginTransaction();
        mostPopularTransaction.add(R.id.most_popular_container,mostPopularFragment);
        nowPlayTransaction.add(R.id.now_playing_container,nowPlayFragment);
        upcomingTransaction.add(R.id.upcoming_container,upcomingFragment);
        topRatedTransaction.add(R.id.top_rated_container,topRatedFragment);
        mostPopularTransaction.commit();
        nowPlayTransaction.commit();
        upcomingTransaction.commit();
        topRatedTransaction.commit();
        return rootView;
    }

}
