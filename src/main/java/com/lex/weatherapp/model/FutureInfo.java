package com.lex.weatherapp.model;

import java.util.Arrays;

/**
 * Created by Administrator on 2015/9/23.
 */
public class FutureInfo {
    /**
     * 白天天气信息
     */
    private String[] day;
    /**
     * 夜晚天气信息
     */
    private String[] night;

    public FutureInfo(String[] day, String[] night) {
        this.day = day;
        this.night = night;
    }

    public String[] getDay() {
        return day;
    }

    public void setDay(String[] day) {
        this.day = day;
    }

    public String[] getNight() {
        return night;
    }

    public void setNight(String[] night) {
        this.night = night;
    }

    @Override
    public String toString() {
        return "FutureInfo{" +
                "day=" + Arrays.toString(day) +
                ", night=" + Arrays.toString(night) +
                '}';
    }
}
