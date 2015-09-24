package com.lex.weatherapp.model;

/**
 * Created by Administrator on 2015/9/23.
 */
public class PM25Info {
    private String curPm;
    private String pm25;
    private String pm10;
    private String level;
    private String quality;
    private String des;

    public PM25Info(String curPm, String pm25, String pm10, String level, String quality, String des) {
        this.curPm = curPm;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.level = level;
        this.quality = quality;
        this.des = des;
    }

    public String getCurPm() {
        return curPm;
    }

    public void setCurPm(String curPm) {
        this.curPm = curPm;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
