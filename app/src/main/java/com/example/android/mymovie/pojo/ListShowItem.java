package com.example.android.mymovie.pojo;

import android.graphics.Bitmap;

public class ListShowItem {
    private Bitmap showImage;
    private String showTitle;
    private double showScore;

    public ListShowItem(Bitmap showImage, String showTitle, double showScore) {
        this.showImage = showImage;
        this.showTitle = showTitle;
        this.showScore = showScore;
    }

    public Bitmap getShowImage() {
        return showImage;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public double getShowScore() {
        return showScore;
    }
}
