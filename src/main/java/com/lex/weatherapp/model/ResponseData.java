package com.lex.weatherapp.model;

/**
 * Created by Administrator on 2015/9/23.
 */
public class ResponseData {
    private String reason;
    private WeatherData weatherData;
    private String errorCode;

    public ResponseData(String reason, WeatherData weatherData, String errorCode) {
        this.reason = reason;
        this.weatherData = weatherData;
        this.errorCode = errorCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "reason='" + reason + '\'' +
                ", weatherData=" + weatherData +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
