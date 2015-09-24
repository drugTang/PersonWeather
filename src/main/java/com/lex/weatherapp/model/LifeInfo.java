package com.lex.weatherapp.model;

import java.util.Arrays;

/**
 * Created by Administrator on 2015/9/23.
 */
public class LifeInfo {
    /**
     * 穿衣
     */
    private String[] dress;
    /**
     * 感冒
     */
    private String[] cold;
    /**
     * 空调
     */
    private String[] airCondition;
    /**
     * 污染
     */
    private String[] pollution;
    /**
     * 洗车
     */
    private String[] carWashing;
    /**
     * 运动
     */
    private String[] sport;
    /**
     * 紫外线
     */
    private String[] ultravioletRay;

    public LifeInfo(String[] dress, String[] cold,
                    String[] airCondition, String[] pollution,
                    String[] carWashing, String[] sport, String[] ultravioletRay) {
        this.dress = dress;
        this.cold = cold;
        this.airCondition = airCondition;
        this.pollution = pollution;
        this.carWashing = carWashing;
        this.sport = sport;
        this.ultravioletRay = ultravioletRay;
    }

    public String[] getDress() {
        return dress;
    }

    public void setDress(String[] dress) {
        this.dress = dress;
    }

    public String[] getCold() {
        return cold;
    }

    public void setCold(String[] cold) {
        this.cold = cold;
    }

    public String[] getAirCondition() {
        return airCondition;
    }

    public void setAirCondition(String[] airCondition) {
        this.airCondition = airCondition;
    }

    public String[] getPollution() {
        return pollution;
    }

    public void setPollution(String[] pollution) {
        this.pollution = pollution;
    }

    public String[] getCarWashing() {
        return carWashing;
    }

    public void setCarWashing(String[] carWashing) {
        this.carWashing = carWashing;
    }

    public String[] getSport() {
        return sport;
    }

    public void setSport(String[] sport) {
        this.sport = sport;
    }

    public String[] getUltravioletRay() {
        return ultravioletRay;
    }

    public void setUltravioletRay(String[] ultravioletRay) {
        this.ultravioletRay = ultravioletRay;
    }

    @Override
    public String toString() {
        return "LifeInfo{" +
                "dress=" + Arrays.toString(dress) +
                ", cold=" + Arrays.toString(cold) +
                ", airCondition=" + Arrays.toString(airCondition) +
                ", pollution=" + Arrays.toString(pollution) +
                ", carWashing=" + Arrays.toString(carWashing) +
                ", sport=" + Arrays.toString(sport) +
                ", ultravioletRay=" + Arrays.toString(ultravioletRay) +
                '}';
    }
}
