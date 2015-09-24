package com.lex.weatherapp.model;

/**
 * Created by Administrator on 2015/9/23.
 */
public class FutureWeather {
    private String date;
    private FutureInfo info;
    private String week;
    private String lunarCalendar;

    public FutureWeather(String date, FutureInfo info, String week, String lunarCalendar) {
        this.date = date;
        this.info = info;
        this.week = week;
        this.lunarCalendar = lunarCalendar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FutureInfo getInfo() {
        return info;
    }

    public void setInfo(FutureInfo info) {
        this.info = info;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getLunarCalendar() {
        return lunarCalendar;
    }

    public void setLunarCalendar(String lunarCalendar) {
        this.lunarCalendar = lunarCalendar;
    }

    @Override
    public String toString() {
        return "FutureWeather{" +
                "date='" + date + '\'' +
                ", info=" + info +
                ", week='" + week + '\'' +
                ", lunarCalendar='" + lunarCalendar + '\'' +
                '}';
    }
}
