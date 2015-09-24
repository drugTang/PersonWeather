package com.lex.weatherapp.model;

/**
 * Created by Administrator on 2015/9/23.
 */
public class LifeData {
    /**
     * 时间
     */
    private String date;
    /**
     * 生活信息
     */
    private LifeInfo lifeInfo;

    public LifeData(String date, LifeInfo lifeInfo) {
        this.date = date;
        this.lifeInfo = lifeInfo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LifeInfo getLifeInfo() {
        return lifeInfo;
    }

    public void setLifeInfo(LifeInfo lifeInfo) {
        this.lifeInfo = lifeInfo;
    }
}
