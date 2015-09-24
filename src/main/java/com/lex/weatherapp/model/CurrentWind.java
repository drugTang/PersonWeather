package com.lex.weatherapp.model;

/**
 * Created by Administrator on 2015/9/23.
 */
public class CurrentWind {
    /**
     * current wind direction
     */
    private String direct;
    /**
     * current wind power
     */
    private String power;
    public CurrentWind(String direct, String power) {
        this.direct = direct;
        this.power = power;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
