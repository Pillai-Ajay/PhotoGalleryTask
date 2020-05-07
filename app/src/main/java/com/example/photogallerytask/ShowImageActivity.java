package com.example.photogallerytask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class ShowImageActivity extends BaseActivity {

    private CustomViewPager viewPager;
    private Handler handler = new Handler();
    private ImageButton btnPrev, btnNext, btnPause, btnPlay;
    private int size, currentPosition, firstPosition, lastPosition, seconds;

    @Override
    protected int getResourceLayoutId() {
        return R.layout.layout_show_image;
    }

    @Override
    protected void afterInflation() {
        btnNext  = (ImageButton) findViewById(R.id.btnNext);
        btnPrev  = (ImageButton) findViewById(R.id.btnPrev);
        btnPlay  = (ImageButton) findViewById(R.id.btnPlay);
        btnPause = (ImageButton) findViewById(R.id.btnStop);
        viewPager = (CustomViewPager) findViewById(R.id.viewPagerShowImage);

        imageURLs = getIntent().getStringArrayListExtra("imageUrls");
        seconds = getIntent().getIntExtra("seconds",2);
        seconds = seconds * 1000;
        Log.e("Seconds are", String.valueOf(seconds));

        size = imageURLs.size();
        firstPosition = 0;
        lastPosition = size - 1;
        currentPosition = getIntent().getIntExtra("selectedImgPos",1);

        final ShowImageAdapter showImageAdapter = new ShowImageAdapter(this,imageURLs, currentPosition);
        viewPager.setAdapter(showImageAdapter);
        viewPager.setCurrentItem(currentPosition, true);
        pageChange();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = viewPager.getCurrentItem();
                if(currentPosition == lastPosition){
                    btnPlay.setVisibility(View.GONE);
                    btnPause.setVisibility(View.VISIBLE);
                    runnable.run();
                    currentPosition = 0;
                }else {
                    btnPlay.setVisibility(View.GONE);
                    btnPause.setVisibility(View.VISIBLE);
                    runnable.run();
                }
            }

        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                btnPlay.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.GONE);
            }
        });


        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() == firstPosition)
                {
                    viewPager.setCurrentItem(lastPosition, true);
                }else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
                    currentPosition = viewPager.getCurrentItem();
                    handler.removeCallbacks(runnable);
                    runnable.run();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem() == lastPosition)
                {
                    viewPager.setCurrentItem(firstPosition, true);
                }else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    currentPosition = viewPager.getCurrentItem();
                    handler.removeCallbacks(runnable);
                    runnable.run();
                }
            }
        });
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (btnPause.getVisibility() == View.VISIBLE)
            {
                handler.postDelayed(this,seconds);
                viewPager.setCurrentItem(currentPosition++, true);
            }
        }
    };

    private void pageChange() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position >= lastPosition ) {
                    currentPosition = 0;
                }
            }

            @Override
            public void onPageSelected(int position) {
                if(position == size-1){
                }else {
                    btnPrev.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                    btnPlay.setVisibility(View.VISIBLE);
                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_SETTLING){
                    currentPosition = viewPager.getCurrentItem();
                    handler.removeCallbacks(runnable);
                    runnable.run();
                }
            }
        });
    }
}
