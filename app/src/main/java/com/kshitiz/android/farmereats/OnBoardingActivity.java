package com.kshitiz.android.farmereats;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.kshitiz.android.farmereats.adapters.SliderAdapter;
import com.kshitiz.android.farmereats.models.SliderModel;

import java.util.ArrayList;

public class OnBoardingActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private ArrayList<SliderModel> sliderModels;
    private TextView[] dots;
    SliderAdapter sliderAdapter;
    View mDecorView;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        mDecorView = getWindow().getDecorView();
        hideSystemUI();

        viewPager = findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.threeDotsLayout);

        sliderModels = new ArrayList<>();
        sliderModels.add(new SliderModel(getString(R.string.title_1), getString(R.string.intro_1), R.drawable.img_onboarding_1, getColor(R.color.onboardingPage1Color)));
        sliderModels.add(new SliderModel(getString(R.string.title_2), getString(R.string.intro_2), R.drawable.img_onboarding_2, getColor(R.color.onboardingPage2Color)));
        sliderModels.add(new SliderModel(getString(R.string.title_3), getString(R.string.intro_3), R.drawable.img_onboarding_3, getColor(R.color.onboardingPage3Color)));

        sliderAdapter = new SliderAdapter(OnBoardingActivity.this, sliderModels, viewPager);
        viewPager.setAdapter(sliderAdapter);

        size = sliderModels.size();
        addDots(size, 0);

        viewPager.addOnPageChangeListener(viewListener);
    }

    private void addDots(int size, int pos) {
        dots = new TextView[size];
        dotsLayout.removeAllViews();

        for (int i = 0; i < size; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("•", 1));
            dots[i].setTextSize(35);

            // below line is called when the dots are not selected.
            dots[i].setTextColor(getColor(R.color.black));
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0) {
            // this line is called when the dots
            // inside linear layout are selected
            dots[pos].setTextColor(getColor(R.color.gray));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            addDots(size, position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {}
    };

    private void hideSystemUI() {
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    private void showSystemUI() {
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}