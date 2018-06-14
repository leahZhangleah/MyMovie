package com.example.android.mymovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.mymovie.adapter.MostPopularPagerAdapter;

public class NavigationFragment extends android.support.v4.app.Fragment {
    public static NavigationFragment newInstance(String title){
        NavigationFragment navigationFragment = new NavigationFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        navigationFragment.setArguments(args);
        return navigationFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigation,container,false);
        ViewPager popularPager = rootView.findViewById(R.id.most_popular_pager);
        MostPopularPagerAdapter mostPopularPagerAdapter = new MostPopularPagerAdapter(getContext());
        //todo:adapter.setlistitems
        popularPager.setAdapter(mostPopularPagerAdapter);
        OneCategoryShowFragment nowPlayFragment = OneCategoryShowFragment.newInstance(getString(R.string.now_playing_label));
        OneCategoryShowFragment upcomingFragment = OneCategoryShowFragment.newInstance(getString(R.string.upcoming_label));
        OneCategoryShowFragment topRatedFragment = OneCategoryShowFragment.newInstance(getString(R.string.top_rated_label));
        FragmentTransaction nowPlayTransaction = getFragmentManager().beginTransaction();
        FragmentTransaction upcomingTransaction = getFragmentManager().beginTransaction();
        FragmentTransaction topRatedTransaction = getFragmentManager().beginTransaction();
        nowPlayTransaction.replace(R.id.now_playing_container,nowPlayFragment);
        upcomingTransaction.replace(R.id.upcoming_container,upcomingFragment);
        topRatedTransaction.replace(R.id.top_rated_container,topRatedFragment);
        nowPlayTransaction.commit();
        upcomingTransaction.commit();
        topRatedTransaction.commit();
        return rootView;
    }
}
