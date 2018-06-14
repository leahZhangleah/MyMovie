package com.example.android.mymovie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.mymovie.R;
import com.example.android.mymovie.pojo.ListShowItem;

import java.util.ArrayList;

public class MostPopularPagerAdapter extends PagerAdapter {
    Context mContext;
    ArrayList<ListShowItem> mListShowItems;
    private static final int SHOW_ITEM_NUM = 6;
    public MostPopularPagerAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return SHOW_ITEM_NUM;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        CardView cardView = (CardView) LayoutInflater.from(mContext).inflate(R.layout.most_popular_pager_item,container,false);
        ImageView mostPopularImgV = cardView.findViewById(R.id.most_popular_image_view);
        TextView mostPopularTitleV = cardView.findViewById(R.id.most_popular_show_title);
        TextView mostPopularScoreV = cardView.findViewById(R.id.most_popular_show_score);
        ListShowItem item = mListShowItems.get(position);
        mostPopularImgV.setImageBitmap(item.getShowImage());
        mostPopularTitleV.setText(item.getShowTitle());
        mostPopularScoreV.setText(String.valueOf(item.getShowScore()));
        return cardView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((CardView)object);
    }

    public void setmListShowItems(ArrayList<ListShowItem> mListShowItems) {
        this.mListShowItems = mListShowItems;
    }
}
