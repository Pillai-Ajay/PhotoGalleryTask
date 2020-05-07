package com.example.photogallerytask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ShowImageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    ArrayList<String> ImgUrl= new ArrayList<>();
    int currentPosition;

    public ShowImageAdapter(Context context, ArrayList<String> imgUrl, int currentPosition) {
        this.context = context;
        this.ImgUrl = imgUrl;
        this.currentPosition = currentPosition;
    }

    @Override
    public int getCount() {
        return ImgUrl.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_view_pager,null);
        PhotoView imageView = (com.github.chrisbanes.photoview.PhotoView) view.findViewById(R.id.ivShowImages);

        Glide.with(this.context)
                .load(ImgUrl.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.noimage)
                .into(imageView);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view,0);

        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}