package com.example.android.mymovie.pojo;

public class ListShowItem {
    private int movieId;
    private int showImage;
    private String showTitle;
    private double showScore;

    public ListShowItem(int showImage, String showTitle, double showScore, int movieId) {
        this.showImage = showImage;
        this.showTitle = showTitle;
        this.showScore = showScore;
        this.movieId = movieId;
    }

    public int getShowImage() {
        return showImage;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public double getShowScore() {
        return showScore;
    }

    public int getMovieId() {
        return movieId;
    }
}
