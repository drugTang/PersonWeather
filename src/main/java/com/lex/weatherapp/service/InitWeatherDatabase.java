package com.lex.weatherapp.service;

import android.content.Context;
import android.widget.Toast;

import com.lex.weatherapp.db.WeatherDB;
import com.lex.weatherapp.util.HttpCallbackListener;
import com.lex.weatherapp.util.HttpUtil;
import com.lex.weatherapp.util.MyApplication;
import com.lex.weatherapp.util.Utility;

/**
 * Created by Administrator on 2015/9/21.
 */
public class InitWeatherDatabase {
    private WeatherDB weatherDB = null;
    private Context context = null;

    private InitWeatherDatabase() {
        context = MyApplication.getContext();
        weatherDB = WeatherDB.getInstance(context);
    }

    private void initProvince() {
        String address = "http://www.weather.com.cn/data/list3/city.xml";
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {

            @Override
            public void onFinish(String response) {
                Utility.handleProvincesResponse(weatherDB, response);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(context,"初始化省份数据失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
