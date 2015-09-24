package com.lex.weatherapp.model;

/**
 * Created by Administrator on 2015/9/24.
 */
public class IndexInfo {
    private int iconId;
    private String titleText;
    private String simpleText;
    private int arrowId;

    public IndexInfo(int iconId, String titleText, String simpleText, int arrowId) {
        this.iconId = iconId;
        this.titleText = titleText;
        this.simpleText = simpleText;
        this.arrowId = arrowId;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getSimpleText() {
        return simpleText;
    }

    public void setSimpleText(String simpleText) {
        this.simpleText = simpleText;
    }

    public int getArrowId() {
        return arrowId;
    }

    public void setArrowId(int arrowId) {
        this.arrowId = arrowId;
    }
}
