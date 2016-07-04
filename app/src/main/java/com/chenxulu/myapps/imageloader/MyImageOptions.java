package com.chenxulu.myapps.imageloader;

/**
 * Created by xulu on 16/6/27.
 */
public class MyImageOptions {
    private int imageResOnLoading = 0;
    private int imageResForEmptyUri = 0;
    private int imageResOnFail = 0;

    private boolean rounded;

    public MyImageOptions() {

    }

    /**
     * 是否显示圆角
     *
     * @param rounded
     */
    public MyImageOptions(boolean rounded) {
        this(0, 0, 0, rounded);
    }

    /**
     * @param imageResOnLoading
     * @param imageResForEmptyUri
     * @param imageResOnFail
     */
    public MyImageOptions(int imageResOnLoading, int imageResForEmptyUri, int imageResOnFail) {
        this(imageResOnLoading, imageResForEmptyUri, imageResOnFail, false);
    }

    /**
     * @param imageResOnLoading
     * @param imageResForEmptyUri
     * @param imageResOnFail
     * @param rounded
     */
    public MyImageOptions(int imageResOnLoading, int imageResForEmptyUri, int imageResOnFail, boolean rounded) {
        this.imageResOnLoading = imageResOnLoading;
        this.imageResForEmptyUri = imageResForEmptyUri;
        this.imageResOnFail = imageResOnFail;
        this.rounded = rounded;
    }

    public int getImageResOnLoading() {
        return imageResOnLoading;
    }

    public void setImageResOnLoading(int imageRes) {
        this.imageResOnLoading = imageRes;
    }

    public int getImageResForEmptyUri() {
        return imageResForEmptyUri;
    }

    public void setImageResForEmptyUri(int imageRes) {
        this.imageResForEmptyUri = imageRes;
    }

    public int getImageResOnFail() {
        return imageResOnFail;
    }

    public void setImageResOnFail(int imageRes) {
        this.imageResOnFail = imageRes;
    }

    public boolean isRounded() {
        return rounded;
    }

    public void setRounded(boolean rounded) {
        this.rounded = rounded;
    }
}
