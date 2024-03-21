package com.kshitiz.android.farmereats;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.kshitiz.android.farmereats.adapters.SignInSliderAdapter;
import com.kshitiz.android.farmereats.custom_classes.NonSwipeableViewPager;
import com.kshitiz.android.farmereats.models.SignInSliderModel;

import java.util.ArrayList;

@SuppressLint("StaticFieldLeak")
public class SignInActivity extends AppCompatActivity {
    public static ProgressBar loadingBar;
    NonSwipeableViewPager viewPager;
    SignInSliderAdapter sliderAdapter;
    ArrayList<SignInSliderModel> sliderModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        viewPager = findViewById(R.id.signInViewPager);
        loadingBar = findViewById(R.id.loadingBar);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            int page = viewPager.getCurrentItem();
            if (page > 0) viewPager.setCurrentItem(page - 1);
        });

        sliderModels = new ArrayList<>();
        sliderModels.add(new SignInSliderModel(R.string.welcome_back, R.string.login));
        sliderModels.add(new SignInSliderModel(R.string.forgot_password, R.string.send_code));
        sliderModels.add(new SignInSliderModel(R.string.verify_otp, R.string.submit));
        sliderModels.add(new SignInSliderModel(R.string.reset_password, R.string.submit));

        sliderAdapter = new SignInSliderAdapter(SignInActivity.this, sliderModels, viewPager);
        viewPager.setAdapter(sliderAdapter);
    }
}