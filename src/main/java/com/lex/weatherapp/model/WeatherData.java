package com.lex.weatherapp.model;

/**
 * Created by Administrator on 2015/9/23.
 */
public class WeatherData {
    private RealtimeWeather realtime;
    private LifeData life;
    private FutureWeather[] weather;
    private PM25Data pm25;

    public WeatherData(RealtimeWeather realtime, LifeData life, FutureWeather[] weather, PM25Data pm25) {
        this.realtime = realtime;
        this.life = life;
        this.weather = weather;
        this.pm25 = pm25;
    }

    public RealtimeWeather getRealtime() {
        return realtime;
    }

    public void setRealtime(RealtimeWeather realtime) {
        this.realtime = realtime;
    }

    public LifeData getLife() {
        return life;
    }

    public void setLife(LifeData life) {
        this.life = life;
    }

    public FutureWeather[] getWeather() {
        return weather;
    }

    public void setWeather(FutureWeather[] weather) {
        this.weather = weather;
    }

    public PM25Data getPm25() {
        return pm25;
    }

    public void setPm25(PM25Data pm25) {
        this.pm25 = pm25;
    }
}
