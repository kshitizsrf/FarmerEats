package com.kshitiz.android.farmereats.custom_classes;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class CustomViewPagerScroller extends Scroller {
    int scrollDuration;
    public CustomViewPagerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public void setScrollDuration(int scrollDuration) {
        this.scrollDuration = scrollDuration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, scrollDuration);
    }
}
