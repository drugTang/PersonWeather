package com.lex.weatherapp.model;

/**
 * Created by Administrator on 2015/9/22.
 */
public class FutureWeatherInfo {
    private String dateTime;
    private String weatherInfo;
    private String temperate;

    public FutureWeatherInfo(String dateTime, String weatherInfo, String temperate) {
        this.dateTime = dateTime;
        this.weatherInfo = weatherInfo;
        this.temperate = temperate;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTemperate() {
        return temperate;
    }

    public void setTemperate(String temperate) {
        this.temperate = temperate;
    }

    public String getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(String weatherInfo) {
        this.weatherInfo = weatherInfo;
    }
}
