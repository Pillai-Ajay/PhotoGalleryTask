package com.example.photogallerytask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ShowimageFragment extends Fragment {

    private View view;
    private ViewPager viewPager;
    private Handler handler = new Handler();
    private ArrayList<String> ImgUrl= new ArrayList<>();
    private ImageButton btnPrev, btnNext, btnPause, btnPlay;
    private int size, currentPosition, firstPosition, lastPosition;
    private boolean state = true;
    int posit,dummy;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.layout_show_image,container,false);

        btnNext  = (ImageButton) view.findViewById(R.id.btnNext);
        btnPrev  = (ImageButton) view.findViewById(R.id.btnPrev);
        btnPlay  = (ImageButton) view.findViewById(R.id.btnPlay);
        btnPause = (ImageButton) view.findViewById(R.id.btnStop);
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerShowImage);

        ImgUrl.add("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Jordan_Lipofsky.jpg/170px-Jordan_Lipofsky.jpg");
        ImgUrl.add("https://cdn.playbuzz.com/cdn/62b7af36-65b7-41aa-8db2-e34fd8a76acf/62c5efd3-fa55-464b-8ee5-9a3e2543c830.jpg");
        ImgUrl.add("https://cdn.playbuzz.com/cdn/fa415381-3e73-4678-915d-7abf8983ce09/813d91c3-f7c9-4a20-9e7b-7e7b6da78941.jpg");
        ImgUrl.add("https://cdn.playbuzz.com/cdn/62b7af36-65b7-41aa-8db2-e34fd8a76acf/1e93e32c-7662-4de7-a441-59d4c29d6faf.jpg");
        ImgUrl.add("https://cdn.playbuzz.com/cdn/5cb29908-40a5-42d4-831d-5bea595bcf05/3e9f0c63-60c6-4a0c-964c-1302d56295da.jpg");
        ImgUrl.add("https://pmcfootwearnews.files.wordpress.com/2015/06/michael-jordan-chicago-bulls.jpg");
        ImgUrl.add("https://healthyceleb.com/wp-content/uploads/2015/03/Michael-Jordan.jpg");
        ImgUrl.add("https://thelegacyproject.co.za/wp-content/uploads/2015/04/Michael_Jordan_Net_Worth.jpg");
        ImgUrl.add("https://www.guinnessworldrecords.com/Images/Michael-Jordan-main_tcm25-15662.jpg");
        ImgUrl.add("https://sportsmockery.com/wp-content/uploads/2015/03/michael-jordan-1600x1200.jpg");
        ImgUrl.add("https://cdn-s3.si.com/s3fs-public/si/dam/assets/13/02/13/130213172915-michael-jordan-05717484-single-image-cut.jpg");
        ImgUrl.add("https://cdn.playbuzz.com/cdn/5cb29908-40a5-42d4-831d-5bea595bcf05/5246cb13-4c32-45ba-89ad-c63cbbcdfde3.jpg");
        ImgUrl.add("https://i.dailymail.co.uk/i/pix/2015/09/24/17/2CB89DDF00000578-0-image-a-1_1443111464150.jpg");

        size = ImgUrl.size();
        firstPosition = 0;
        lastPosition = size - 1;
        currentPosition = getArguments().getInt("position");

        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext(),ImgUrl, currentPosition);
        viewPager.setAdapter(viewPagerAdapter);
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
                Log.e("In if and position is :", String.valueOf(viewPager.getCurrentItem()));
                if(viewPager.getCurrentItem() == lastPosition)
                {
                    viewPager.setCurrentItem(firstPosition, true);
                }else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    currentPosition = viewPager.getCurrentItem();
                    handler.removeCallbacks(runnable);
                    runnable.run();
                }
                Log.e("In if and position is :", String.valueOf(viewPager.getCurrentItem()));
            }
        });
             return view;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
                if (btnPause.getVisibility() == View.VISIBLE)
                {
                    handler.postDelayed(this,5000);
                    viewPager.setCurrentItem(currentPosition++, true);
                }
//                else if (viewPager.getCurrentItem() == size-1)
//                {
//                    viewPager.setCurrentItem(1, true);
//                    handler.postDelayed(this,5000);
//                }
        }
    };

    private void pageChange() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            float sumPositionAndPositionOffset;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if(viewPager.getCurrentItem() == firstPosition){
//                    if(position+positionOffset > sumPositionAndPositionOffset){
//                        Log.e("Right to","Left");
//                    }else {
//                        Log.e("Left to","Right");
//                    }
//                }
                if(position >= lastPosition ) {
                    currentPosition = 0;
                }
//                sumPositionAndPositionOffset = position + positionOffset;
            }

            @Override
            public void onPageSelected(int position) {
               if(position == size-1){
//                    runnable.run();
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
//                else if (state == ViewPager.SCROLL_STATE_DRAGGING){
//                    if(viewPager.getCurrentItem() == lastPosition){
//                        posit = lastPosition;
//                    }
//                }else if (state == ViewPager.SCROLL_STATE_IDLE){
//                    if (posit == lastPosition){
//                        viewPager.setCurrentItem(0,true);
//                        posit = viewPager.getCurrentItem();
//                    }
//                }
            }
        });
    }
}
