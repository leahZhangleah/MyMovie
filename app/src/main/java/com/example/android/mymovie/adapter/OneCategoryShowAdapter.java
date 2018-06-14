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

import com.example.android.mymovie.R;
import com.example.android.mymovie.pojo.ListShowItem;

import java.util.ArrayList;

public class OneCategoryShowAdapter extends RecyclerView.Adapter<OneCategoryShowAdapter.CategoryViewholder> {
    Context mContext;
    ArrayList<ListShowItem> mListShowItems;
    private static final int SHOW_ITEM_NUM = 6;
    public OneCategoryShowAdapter(Context context){
        mContext = context;
    }
    @NonNull
    @Override
    public OneCategoryShowAdapter.CategoryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(mContext).inflate(R.layout.fragment_one_category_show,parent,false);
        CategoryViewholder categoryViewholder = new CategoryViewholder(cardView);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OneCategoryShowAdapter.CategoryViewholder holder, int position) {
        ListShowItem showItem = mListShowItems.get(position);
        holder.showImgV.setImageBitmap(showItem.getShowImage());
        holder.showTitleV.setText(showItem.getShowTitle());
        holder.showScoreV.setText(String.valueOf(showItem.getShowScore()));
    }

    @Override
    public int getItemCount() {
        return SHOW_ITEM_NUM;
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
    }

    public void setmListShowItems(ArrayList<ListShowItem> mListShowItems) {
        this.mListShowItems = mListShowItems;
    }
}
