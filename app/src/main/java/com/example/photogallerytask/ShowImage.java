package com.example.photogallerytask;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;

public class ShowImage {
    private Activity mActivity;
    private ArrayList<String> mImageUrls;
    private int selectedImgPos;

    private ShowImage(){

    }

    public static ShowImage with (Activity mActivity, ArrayList<String> mImageUrls){
        return new ShowImage(mActivity, mImageUrls);
    }

    private ShowImage(Activity mActivity, ArrayList<String> mImageUrls){
        this.mActivity = mActivity;
        this.mImageUrls = mImageUrls;
    }

    public ShowImage selectedImgPos(int selectedImgPos){
        this.selectedImgPos = selectedImgPos;
        return this;
    }

    public void show(){
        Intent showImageFragment = new Intent(mActivity, ShowimageFragment.class);
        showImageFragment.putExtra("imageUrls",mImageUrls);
        showImageFragment.putExtra("selectedImgPos",selectedImgPos);
        mActivity.startActivity(showImageFragment);
    }
}
