package com.example.photogallerytask;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.constraintlayout.widget.ConstraintLayout;

public class HackyProblematicViewGroup extends ConstraintLayout {

    public HackyProblematicViewGroup(Context context) {
        super(context);
    }

    public HackyProblematicViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HackyProblematicViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (Exception e) {
						//uncomment if you really want to see these errors
            //e.printStackTrace();
            return false;
        }
    }

}