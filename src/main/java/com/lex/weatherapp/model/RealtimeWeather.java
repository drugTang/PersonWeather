package com.lex.weatherapp.model;

/**
 * Created by Administrator on 2015/9/23.
 */
public class RealtimeWeather {
    /**
     * City Code of you query
     */
    private String cityCode;
    /**
     * City Name of you query
     */
    private String cityName;
    /**
     * Date without time when you query
     */
    private String date;
    /**
     * Latest time update data
     */
    private String time;
    /**
     * week when you query
     */
    private String week;
    /**
     * lunar calender
     */
    private String moon;
    /**
     * 数据正常运行的时间
     */
    private String dataUptime;
    /**
     * 当前的查询到的天气信息
     */
    private CurrentWeather currentWeather;
    /**
     * 当前查询到的风向信息
     */
    private CurrentWind currentWind;

    public RealtimeWeather(String cityCode, String cityName,
                           String date, String time, String week,
                           String moon, String dataUptime,
                           CurrentWeather currentWeather, CurrentWind currentWind) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.date = date;
        this.time = time;
        this.week = week;
        this.moon = moon;
        this.dataUptime = dataUptime;
        this.currentWeather = currentWeather;
        this.currentWind = currentWind;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getMoon() {
        return moon;
    }

    public void setMoon(String moon) {
        this.moon = moon;
    }

    public String getDataUptime() {
        return dataUptime;
    }

    public void setDataUptime(String dataUptime) {
        this.dataUptime = dataUptime;
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public CurrentWind getCurrentWind() {
        return currentWind;
    }

    public void setCurrentWind(CurrentWind currentWind) {
        this.currentWind = currentWind;
    }
}
