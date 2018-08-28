package com.example.xyzreader.ui;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class CustomPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;
    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();
        if (position < -1) { // [-Infinity,-1)
            // move page from off-screen to the left.
            page.setAlpha(0);
        } else if (position <= 0) { // [-1,0]
            // Setting default slide transition when moving to the left page
            page.setAlpha(1);
            page.setTranslationX(0);
            page.setScaleX(1);
            page.setScaleY(1);
        } else if (position <= 1) { // (0,1]
            // Fading page out.
            page.setAlpha(1 - position);
            page.setTranslationX(pageWidth * -position);
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        } else { // (1,+Infinity]
            // move page is from off-screen to the right.
            page.setAlpha(0);
        }
    }

}
