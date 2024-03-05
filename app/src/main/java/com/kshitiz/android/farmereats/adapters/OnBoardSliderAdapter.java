package com.kshitiz.android.farmereats.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.kshitiz.android.farmereats.R;
import com.kshitiz.android.farmereats.SignInActivity;
import com.kshitiz.android.farmereats.models.OnBoardSliderModel;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class OnBoardSliderAdapter extends PagerAdapter {
    Context context;
    ViewPager viewPager;
    LayoutInflater inflater;
    ArrayList<OnBoardSliderModel> sliderModels;

    public OnBoardSliderAdapter(Context context, ArrayList<OnBoardSliderModel> sliderModels, ViewPager viewPager) {
        this.context = context;
        this.sliderModels = sliderModels;
        this.viewPager = viewPager;
    }

    @Override
    public int getCount() {
        return sliderModels.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.slider_onboarding, container, false);

        TextView loginButton = view.findViewById(R.id.loginButton);
        TextView onboardTitleTextView = view.findViewById(R.id.onboardTitleTextView);
        TextView onboardIntroTextView = view.findViewById(R.id.onboardIntroTextView);
        ImageView onboardBgImageView = view.findViewById(R.id.onboardBgImageView);
        Button joinButton = view.findViewById(R.id.joinButton);

        OnBoardSliderModel model = sliderModels.get(position);

        onboardBgImageView.setBackgroundResource(model.getBgImageId());
        onboardTitleTextView.setText(model.getTitleText());
        onboardIntroTextView.setText(model.getIntroText());
        view.setBackgroundColor(model.getBgColorId());
        joinButton.setBackgroundTintList(ColorStateList.valueOf(model.getBgColorId()));

        joinButton.setOnClickListener(v -> {
            if (position < getCount() - 1) {
                viewPager.setCurrentItem(position + 1);
            }
            else {
                // Handle the case when you are on the last slide
                // You might want to navigate to another activity or perform a specific action
            }
        });
        loginButton.setOnClickListener(v -> context.startActivity(new Intent(context, SignInActivity.class)));

        container.addView(view);

        setViewPagerScrollSpeed();

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }

    private void setViewPagerScrollSpeed() {
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);

            CustomViewPagerScroller scroller = new CustomViewPagerScroller(viewPager.getContext(), new DecelerateInterpolator());
            scroller.setScrollDuration(300);

            scrollerField.set(viewPager, scroller);
        }
        catch (Exception e) {
            Log.e("SetScrollSpeed", "setViewPagerScrollSpeed: " + e.getMessage(), e);
        }
    }
}
