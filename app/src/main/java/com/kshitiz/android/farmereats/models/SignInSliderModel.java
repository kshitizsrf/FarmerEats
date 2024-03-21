package com.kshitiz.android.farmereats.models;

public class SignInSliderModel {
    int titleStringId, btnStringId;

    public SignInSliderModel(int titleStringId, int btnStringId) {
        this.titleStringId = titleStringId;
        this.btnStringId = btnStringId;
    }

    public int getTitleStringId() {
        return titleStringId;
    }

    public int getBtnStringId() {
        return btnStringId;
    }
}
