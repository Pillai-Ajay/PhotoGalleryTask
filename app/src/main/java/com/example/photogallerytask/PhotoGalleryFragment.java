package com.example.photogallerytask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public final class PhotoGalleryFragment extends BaseActivity {

    View view;
    RecyclerView rvDisplayPhotos;
    int spanCount;

    @Override
    protected int getResourceLayoutId() {
        return R.layout.layout_photo_gallery;
    }

    @Override
    protected void afterInflation() {
        rvDisplayPhotos = (RecyclerView) findViewById(R.id.rvDisplayPhotos);
        rvDisplayPhotos.setHasFixedSize(true);

        spanCount = getIntent().getIntExtra("spanCount",1);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,spanCount, LinearLayoutManager.VERTICAL,false);
        rvDisplayPhotos.setLayoutManager(gridLayoutManager);
        PhotoGalleryAdapter photoGalleryAdapter = new PhotoGalleryAdapter(imageURLs, this);
        rvDisplayPhotos.setAdapter(photoGalleryAdapter);
    }
}
