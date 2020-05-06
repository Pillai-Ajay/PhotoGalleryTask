package com.example.photogallerytask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.util.ArrayList;

public abstract class BaseActivity extends AppCompatActivity {

    protected ArrayList<String> imageURLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayoutId());
        imageURLs = getIntent().getStringArrayListExtra("imageURLs");
        afterInflation();
    }

    protected abstract int getResourceLayoutId();

    protected abstract void afterInflation();
}
