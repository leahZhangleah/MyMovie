package com.example.android.mymovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.mymovie.adapter.OneCategoryShowAdapter;

public class OneCategoryShowFragment extends android.support.v4.app.Fragment {
    public static OneCategoryShowFragment newInstance(String category){
        OneCategoryShowFragment oneCategoryShowFragment = new OneCategoryShowFragment();
        Bundle args = new Bundle();
        args.putString("category",category);
        oneCategoryShowFragment.setArguments(args);
        return oneCategoryShowFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        String category = getArguments().getString("category");
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_one_category_show,container,false);
        RecyclerView categoryRecyclerView = rootView.findViewById(R.id.show_recyclerview);
        OneCategoryShowAdapter oneCategoryShowAdapter = new OneCategoryShowAdapter(getContext());
        //todo:adapter.setlistitems
        categoryRecyclerView.setAdapter(oneCategoryShowAdapter);
        return rootView;
    }

}
