package com.lex.weatherapp.model;

/**
 * Created by Administrator on 2015/9/23.
 */
public class CurrentWeather {
    /**
     * current temperature
     */
    private String temperature;
    /**
     * current humidity
     */
    private String humidity;
    /**
     * current weather information
     */
    private String info;

    public CurrentWeather(String temperature, String humidity, String info) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.info = info;
    }

    public String getTemperature() {
        return temperature;
    }


    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
