package com.kshitiz.android.farmereats.models;

public class OnBoardSliderModel {
    private final String titleText, introText;
    private final int bgImageId, bgColorId;

    public OnBoardSliderModel(String titleText, String introText, int bgImageId, int bgColorId) {
        this.titleText = titleText;
        this.introText = introText;
        this.bgImageId = bgImageId;
        this.bgColorId = bgColorId;
    }

    public String getTitleText() {
        return titleText;
    }
    public String getIntroText() {
        return introText;
    }
    public int getBgImageId() {
        return bgImageId;
    }
    public int getBgColorId() {
        return bgColorId;
    }
}
