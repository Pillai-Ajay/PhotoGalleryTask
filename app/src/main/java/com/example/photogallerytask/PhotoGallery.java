package com.example.photogallerytask;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;

public class PhotoGallery {

    private Activity mActivity;
    private ArrayList<String> imagesURLs;
    private int spanCount = 1;

    public static PhotoGallery with(Activity activity, ArrayList<String> imagesURLs) {
        return new PhotoGallery(activity, imagesURLs);
    }


    private PhotoGallery(Activity activity, ArrayList<String> imagesURLs) {
        this.imagesURLs = imagesURLs;
        this.mActivity = activity;
    }

    public PhotoGallery setSpanCount(int count) {
        this.spanCount = count;
        return this;
    }

    public void show() {
        Intent gridActivity = new Intent(mActivity, PhotoGalleryActivity.class);
        gridActivity.putExtra("imageURLs", imagesURLs);
        gridActivity.putExtra("spanCount", spanCount);
        mActivity.startActivity(gridActivity);
    }
}
