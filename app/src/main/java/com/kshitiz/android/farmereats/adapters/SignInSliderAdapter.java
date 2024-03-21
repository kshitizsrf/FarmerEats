package com.kshitiz.android.farmereats.adapters;

import static androidx.constraintlayout.widget.ConstraintSet.BOTTOM;
import static androidx.constraintlayout.widget.ConstraintSet.GONE;
import static androidx.constraintlayout.widget.ConstraintSet.TOP;
import static androidx.constraintlayout.widget.ConstraintSet.VISIBLE;
import static com.kshitiz.android.farmereats.SignInActivity.loadingBar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.kshitiz.android.farmereats.R;
import com.kshitiz.android.farmereats.custom_classes.CustomViewPagerScroller;
import com.kshitiz.android.farmereats.custom_classes.NonSwipeableViewPager;
import com.kshitiz.android.farmereats.models.SignInSliderModel;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SignInSliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    ConstraintSet constraintSet;
    NonSwipeableViewPager viewPager;
    ArrayList<SignInSliderModel> sliderModels;
    Button submitButton;
    ConstraintLayout loginSliderLayout;
    LinearLayout otpEditTextLayout, otherButtonsLayout;
    EditText emailEditText, passwdEditText, otpEditText1, otpEditText2, otpEditText3, otpEditText4, otpEditText5;
    TextView titleTextView, promptTextView, promptActionButton, forgotPasswdButton, resendOtpButton, otherLoginText;

    public SignInSliderAdapter(Context context, ArrayList<SignInSliderModel> sliderModels, NonSwipeableViewPager viewPager) {
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

        View view = inflater.inflate(R.layout.slider_sign_in, container, false);

        loginSliderLayout = view.findViewById(R.id.loginSliderLayout);
        constraintSet = new ConstraintSet();
        constraintSet.clone(loginSliderLayout);

        titleTextView = view.findViewById(R.id.signInTitleTextView);
        promptTextView = view.findViewById(R.id.promptTextView);
        promptActionButton = view.findViewById(R.id.promptActionButton);
        forgotPasswdButton = view.findViewById(R.id.forgotPasswdButton);
        resendOtpButton = view.findViewById(R.id.resendOtpButton);
        otherLoginText = view.findViewById(R.id.otherLoginText);

        emailEditText = view.findViewById(R.id.emailEditText);
        passwdEditText = view.findViewById(R.id.passwdEditText);
        otpEditText1 = view.findViewById(R.id.otpEditText1);
        otpEditText2 = view.findViewById(R.id.otpEditText2);
        otpEditText3 = view.findViewById(R.id.otpEditText3);
        otpEditText4 = view.findViewById(R.id.otpEditText4);
        otpEditText5 = view.findViewById(R.id.otpEditText5);

        otpEditTextLayout = view.findViewById(R.id.otpEditTextLayout);
        otherButtonsLayout = view.findViewById(R.id.otherButtonsLayout);

        submitButton = view.findViewById(R.id.loginSubmitButton);

        SignInSliderModel model = sliderModels.get(position);

        titleTextView.setText(model.getTitleStringId());
        submitButton.setText(model.getBtnStringId());

        if (position > 0) {
            switchToForgotPasswdLayout(R.string.remember_your_password, R.string.login);
            switch (position) {
                case 1:
                    emailEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_phone, 0, 0, 0);
                    emailEditText.setHint(R.string.phone_number);
                    constraintSet.connect(R.id.loginSubmitButton, TOP, R.id.emailEditText, BOTTOM);
                    break;
                case 2:
                    constraintSet.setVisibility(R.id.emailEditText, GONE);
                    constraintSet.setVisibility(R.id.otpEditTextLayout, VISIBLE);
                    constraintSet.connect(R.id.loginSubmitButton, TOP, R.id.otpEditTextLayout, BOTTOM);
                    constraintSet.setVisibility(R.id.resendOtpButton, VISIBLE);
                    break;
                case 3:
                    constraintSet.setVisibility(R.id.passwdEditText, VISIBLE);
                    emailEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock, 0, 0, 0);
                    emailEditText.setHint(R.string.new_password);
                    passwdEditText.setHint(R.string.confirm_new_password);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + position);
            }
            constraintSet.applyTo(loginSliderLayout);
        }
        forgotPasswdButton.setOnClickListener(v -> {
            loadingBar.setVisibility(View.VISIBLE);
            loadingBar.postDelayed(() -> {
                viewPager.setCurrentItem(1);
                loadingBar.setVisibility(View.GONE);
            }, 500);
        });
        submitButton.setOnClickListener(v -> {
            switch (position) {
                case 0:
                    hitLoginApi();
                    break;
                case 1:
                    viewPager.setCurrentItem(2);
                    sendPasswdResetCode();
                    break;
                case 2:
                    viewPager.setCurrentItem(3);
                    confirmPasswdResetCode();
                    break;
                case 3:
                    viewPager.setCurrentItem(0);
                    setNewPasswd();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + position);
            }
        });

        container.addView(view);
        setViewPagerScrollSpeed();
        return view;
    }

    private void setNewPasswd() {}

    private void confirmPasswdResetCode() {}

    private void sendPasswdResetCode() {}

    private void hitLoginApi() {}

    public void switchToForgotPasswdLayout(int promptStringId, int actionStringId) {
        promptTextView.setText(promptStringId);
        promptActionButton.setText(actionStringId);
        constraintSet.setVisibility(R.id.passwdEditText, GONE);
        constraintSet.setVisibility(R.id.forgotPasswdButton, GONE);
        constraintSet.setVisibility(R.id.otherLoginText, GONE);
        constraintSet.setVisibility(R.id.otherButtonsLayout, GONE);
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
            scroller.setScrollDuration(500);

            scrollerField.set(viewPager, scroller);
        }
        catch (Exception e) {
            Log.e("SetScrollSpeed", "setViewPagerScrollSpeed: " + e.getMessage(), e);
        }
    }
}
