package com.example.android.mymovie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.mymovie.R;
import com.example.android.mymovie.pojo.Result;
import com.example.android.mymovie.sync.NetworkInfo;

import java.util.ArrayList;

public class OneCategoryShowAdapter extends RecyclerView.Adapter<OneCategoryShowAdapter.CategoryViewholder> {
    Context mContext;
    ArrayList<Result> results;
    private static final int SHOW_ITEM_NUM = 6;
    public OneCategoryShowAdapter(Context context){
        mContext = context;
    }
    @NonNull
    @Override
    public OneCategoryShowAdapter.CategoryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView roootView = (CardView) LayoutInflater.from(mContext).inflate(R.layout.one_category_show_item,parent,false);
        CategoryViewholder categoryViewholder = new CategoryViewholder(roootView);
        return categoryViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull OneCategoryShowAdapter.CategoryViewholder holder, int position) {
        if (results!=null){
            Result show = results.get(position);
            holder.bindData(show);
        }


        /*Uri imageUri = new Uri.Builder()
                .authority(com.example.android.mymovie.sync.NetworkInfo.IMAGE_BASE_URL)
                .appendPath(imagePath).build();*/

    }


    @Override
    public int getItemCount() {
        if (results!=null){
            return results.size();
        }
        return 0;
    }

    public class CategoryViewholder extends RecyclerView.ViewHolder{
        ImageView showImgV;
        TextView showTitleV,showScoreV;
        public CategoryViewholder(View itemView) {
            super(itemView);
            showImgV = itemView.findViewById(R.id.one_category_show_image);
            showTitleV = itemView.findViewById(R.id.one_category_show_title);
            showScoreV = itemView.findViewById(R.id.one_category_show_score);
        }

        public void bindData(Result show){
            String imagePath = show.getPosterPath();
            Glide.with(mContext).load(NetworkInfo.IMAGE_BASE_URL+imagePath).into(showImgV);
            showTitleV.setText(show.getTitle());
            showScoreV.setText(String.valueOf(show.getVoteAverage()));
        }
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
        notifyDataSetChanged();
    }
}
