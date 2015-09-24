package com.lex.weatherapp.model;

/**
 * Created by Administrator on 2015/9/23.
 */
public class PM25Data {
    private String key;
    private String showDesc;
    private PM25Info pm25;
    private String dateTime;
    private String cityName;

    public PM25Data(String key, String showDesc, PM25Info pm25, String dateTime, String cityName) {
        this.key = key;
        this.showDesc = showDesc;
        this.pm25 = pm25;
        this.dateTime = dateTime;
        this.cityName = cityName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getShowDesc() {
        return showDesc;
    }

    public void setShowDesc(String showDesc) {
        this.showDesc = showDesc;
    }

    public PM25Info getPm25() {
        return pm25;
    }

    public void setPm25(PM25Info pm25) {
        this.pm25 = pm25;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
