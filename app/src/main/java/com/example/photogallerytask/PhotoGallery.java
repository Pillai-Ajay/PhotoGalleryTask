package com.example.photogallerytask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
//        Intent gridActivity = new Intent(mActivity, ZGridActivity.class);
//        gridActivity.putExtra("imageURLs", imagesURLs);
//        gridActivity.putExtra("spanCount", spanCount);
//        mActivity.startActivity(gridActivity);

//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.add(R.id.frame_container, new PhotoGalleryFragment());
//        fragmentTransaction.commit();


        Intent gridActivity = new Intent(mActivity, PhotoGalleryFragment.class);
        gridActivity.putExtra("imageURLs", imagesURLs);
        gridActivity.putExtra("spanCount", spanCount);
        mActivity.startActivity(gridActivity);

//        Bundle bundle=new Bundle();
//        bundle.putStringArrayList("imageURLs",imagesURLs);
//        bundle.putInt("spanCount", spanCount);
//        PhotoGalleryFragment fragment=new PhotoGalleryFragment();
//        fragment.setArguments(bundle);
//        String tag = "my_fragment";
//        FragmentManager fragmentManager = ((BaseActivity) mActivity).getSupportFragmentManager();
//
//        if(fragmentManager.findFragmentByTag(tag) == null) {
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frame_container, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//        }
    }
}
