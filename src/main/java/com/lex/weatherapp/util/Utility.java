package com.lex.weatherapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.lex.weatherapp.db.WeatherDB;
import com.lex.weatherapp.model.City;
import com.lex.weatherapp.model.County;
import com.lex.weatherapp.model.CurrentWeather;
import com.lex.weatherapp.model.CurrentWind;
import com.lex.weatherapp.model.FutureInfo;
import com.lex.weatherapp.model.FutureWeather;
import com.lex.weatherapp.model.LifeData;
import com.lex.weatherapp.model.LifeInfo;
import com.lex.weatherapp.model.PM25Data;
import com.lex.weatherapp.model.PM25Info;
import com.lex.weatherapp.model.Province;
import com.lex.weatherapp.model.RealtimeWeather;
import com.lex.weatherapp.model.ResponseData;
import com.lex.weatherapp.model.WeatherData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 工具类，用于存储数据到数据库和SharedPerferences
 */
public class Utility {

    public synchronized static boolean handleProvincesResponse(WeatherDB weatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces.length > 0) {
                for(String p : allProvinces) {
                    String[] array = p.split("|");
                    Province province = new Province();
                    province.setCode(array[0]);
                    province.setName(array[1]);
                    weatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    public synchronized static boolean handleCitiesResponse(WeatherDB weatherDB, String response,
             int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities.length > 0) {
                for(String c : allCities) {
                    String[] array = c.split("|");
                    City city = new City();
                    city.setCode(array[0]);
                    city.setName(array[1]);
                    city.setProvinceId(provinceId);
                    weatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    public synchronized static boolean handleCountiesResponse(WeatherDB weatherDB, String response,
             int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties.length > 0) {
                for(String c : allCounties) {
                    String[] array = c.split("|");
                    County county = new County();
                    county.setCode(array[0]);
                    county.setName(array[1]);
                    county.setCityId(cityId);
                    weatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }

    public synchronized static boolean handleJsonWithJsonObject(Context context, String response) {
        try {
            ResponseData responseData;
            JSONObject jsonObject = new JSONObject(response);
            String reason = jsonObject.getString("reason");
            String errorCode = jsonObject.getString("error_code");
            if(errorCode.equals("0")) {
                JSONObject result = jsonObject.getJSONObject("result");
                JSONObject data = result.getJSONObject("data");
                JSONObject realtime = data.getJSONObject("realtime");
                RealtimeWeather realtimeWeather = handleResponseOfRealtime(realtime);
                JSONObject life = data.getJSONObject("life");
                LifeData lifeData = handleResponseOfLifeData(life);
                JSONArray weather= data.getJSONArray("weather");
                FutureWeather[] weathers = handleResponseOfFutureWeather(weather);
                JSONObject pm25 = data.getJSONObject("pm25");
                PM25Data pm25Data = handleResponseOfPM25(pm25);
                WeatherData weatherData = new WeatherData(realtimeWeather,lifeData,weathers,pm25Data);
                responseData = new ResponseData(reason,weatherData,errorCode);
            } else {
                responseData = new ResponseData(reason,null,errorCode);
            }
            saveWithSharedPerference(context,responseData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void saveWithSharedPerference(Context context, ResponseData data) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.clear();
        String errorCode = data.getErrorCode();
        if (errorCode.equals("0")) {
            WeatherData weatherData = data.getWeatherData();
            saveRealtimeWeather(weatherData,editor);
            saveLifeData(weatherData,editor);
            saveFutureWeather(weatherData, editor);
            savePM25Data(weatherData,editor);
        }
        editor.apply();
    }

    private static void saveLifeData(WeatherData weatherData, SharedPreferences.Editor editor) {
        LifeData life = weatherData.getLife();
        LifeInfo lifeInfo = life.getLifeInfo();
        String[] dress = lifeInfo.getDress();
        String[] cold = lifeInfo.getCold();
        String[] airCondition = lifeInfo.getAirCondition();
        String[] pollution = lifeInfo.getPollution();
        String[] carWashing = lifeInfo.getCarWashing();
        String[] sport = lifeInfo.getSport();
        String[] ultravioletRay = lifeInfo.getUltravioletRay();
        saveStringArray(dress,"dress",editor);
        saveStringArray(cold,"cold",editor);
        saveStringArray(airCondition,"airCondition",editor);
        saveStringArray(pollution,"pollution",editor);
        saveStringArray(carWashing,"carWashing",editor);
        saveStringArray(sport,"sport",editor);
        saveStringArray(ultravioletRay,"ultravioletRay",editor);
    }

    private static void saveStringArray(String[] array,String name, SharedPreferences.Editor editor) {
        if(array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                editor.putString(name+i,array[i]);
            }
        }
    }

    private static void savePM25Data(WeatherData weatherData, SharedPreferences.Editor editor) {
        PM25Data pm25Data = weatherData.getPm25();
        String dateTime = pm25Data.getDateTime();
        PM25Info pm = pm25Data.getPm25();
        String currentPm = pm.getCurPm();
        String pm25 = pm.getPm25();
        String pm10 = pm.getPm10();
        String level = pm.getLevel();
        String quality = pm.getQuality();
        String des = pm.getDes();
        editor.putString("dateTime",dateTime.substring(5));
        editor.putString("currentPm",currentPm);
        editor.putString("pm25",pm25);
        editor.putString("pm10",pm10);
        editor.putString("level",level);
        editor.putString("quality",quality);
        editor.putString("des",des);
    }

    private static void saveFutureWeather(WeatherData weatherData, SharedPreferences.Editor editor) {
        FutureWeather[] weather = weatherData.getWeather();
        for(int i = 0;i < weather.length;++i) {
            String weatherDate = weather[i].getDate();
            String weatherWeek = weather[i].getWeek();
            String lunarCalendar = weather[i].getLunarCalendar();
            editor.putString("weather"+i+"weatherDate",StringUtil.numberDateToDate(weatherDate));
            editor.putString("weather"+i+"weatherWeek",weatherWeek);
            editor.putString("weather"+i+"lunarCalendar",lunarCalendar);
            FutureInfo info = weather[i].getInfo();
            String[] day = info.getDay();
            for (int j = 0;j < day.length;++j) {
                editor.putString("weather"+i+"day"+j,day[j]);
            }
            String[] night = info.getNight();
            for (int k = 0;k < night.length;++k) {
                editor.putString("weather"+i+"night"+k,night[k]);
            }
        }
    }

    private static void saveRealtimeWeather (WeatherData weatherData, SharedPreferences.Editor editor) {
        RealtimeWeather realtime = weatherData.getRealtime();
        String cityCode = realtime.getCityCode();
        editor.putString("cityCode",cityCode);
        String cityName = realtime.getCityName();
        editor.putString("cityName", cityName);
        String date = realtime.getDate();
        editor.putString("date",date);
        String time = realtime.getTime();
        editor.putString("time",time);
        String week = realtime.getWeek();
        editor.putString("week",week);
        String moon = realtime.getMoon();
        editor.putString("moon",moon);
        String dataUptime = realtime.getDataUptime();
        editor.putString("dataUptime",dataUptime);
        CurrentWeather currentWeather = realtime.getCurrentWeather();
        String info = currentWeather.getInfo();
        editor.putString("info",info);
        String temperature = currentWeather.getTemperature();
        editor.putString("temperature",temperature);
        String humidity = currentWeather.getHumidity();
        editor.putString("humidity",humidity);
        CurrentWind currentWind = realtime.getCurrentWind();
        String direct = currentWind.getDirect();
        editor.putString("direct",direct);
        String power = currentWind.getPower();
        editor.putString("power",power);
    }

    private static PM25Data handleResponseOfPM25(JSONObject pm) throws JSONException{
        String key = pm.getString("key");
        String showDesc = pm.getString("show_desc");
        JSONObject pmInfo = pm.getJSONObject("pm25");
        String curPm = pmInfo.getString("curPm");
        String pm25 = pmInfo.getString("pm25");
        String pm10 = pmInfo.getString("pm10");
        String level = pmInfo.getString("level");
        String quality = pmInfo.getString("quality");
        String des = pmInfo.getString("des");
        PM25Info pm25Info = new PM25Info(curPm,pm25,pm10,level,quality,des);
        String dateTime = pm.getString("dateTime");
        String cityName = pm.getString("cityName");
        return new PM25Data(key,showDesc,pm25Info,dateTime,cityName);

    }

    private static FutureWeather[] handleResponseOfFutureWeather(JSONArray futureWeather) throws  JSONException {
        FutureWeather[] futureWeathers = new FutureWeather[futureWeather.length()];
        for(int i = 0;i < futureWeather.length();i++) {
            JSONObject temp = futureWeather.getJSONObject(i);
            String date = temp.getString("date");
            JSONObject futureInfo = temp.getJSONObject("info");
            FutureInfo fInfo = handleResponseOfFutureInfo(futureInfo);
            String week = temp.getString("week");
            String lunarCalendar = temp.getString("nongli");
            futureWeathers[i] = new FutureWeather(date,fInfo,week,lunarCalendar);

        }
        return futureWeathers;
    }

    private static FutureInfo handleResponseOfFutureInfo(JSONObject futureInfo) throws JSONException {
        String day = futureInfo.getString("day");
        String night = futureInfo.getString("night");
        String[] dayArray = StringUtil.stringToArray(day);
        String[] nightArray = StringUtil.stringToArray(night);
        return new FutureInfo(dayArray,nightArray);
    }

    private static LifeData handleResponseOfLifeData(JSONObject life) throws JSONException {
        String date = life.getString("date");
        JSONObject info = life.getJSONObject("info");
        LifeInfo lifeInfo = handleResponseOfLifeInfo(info);
        return new LifeData(date,lifeInfo);
    }

    private static LifeInfo handleResponseOfLifeInfo(JSONObject info) throws JSONException {
        String dress = info.getString("chuanyi");
        String cold = info.getString("ganmao");
        String airCondition = info.getString("kongtiao");
        String pollution = info.getString("wuran");
        String carWashing = info.getString("xiche");
        String sport = info.getString("yundong");
        String ultravioletRay = info.getString("ziwaixian");
        String[] dressArray = StringUtil.stringToArray(dress);
        String[] coldArray = StringUtil.stringToArray(cold);
        String[] airConditionArray = StringUtil.stringToArray(airCondition);
        String[] pollutionArray = StringUtil.stringToArray(pollution);
        String[] carWashingArray = StringUtil.stringToArray(carWashing);
        String[] sportArray = StringUtil.stringToArray(sport);
        String[] ultravioletRayArray = StringUtil.stringToArray(ultravioletRay);
        return new LifeInfo(dressArray,coldArray,airConditionArray,
                pollutionArray,carWashingArray,sportArray,ultravioletRayArray);
    }

    private static RealtimeWeather handleResponseOfRealtime(JSONObject realtime) throws JSONException {
        String cityCode = realtime.getString("city_code");
        String cityName = realtime.getString("city_name");
        String date = realtime.getString("date");
        String time = realtime.getString("time");
        String week = realtime.getString("week");
        String moon = realtime.getString("moon");
        String dataUptime = realtime.getString("dataUptime");
        JSONObject weather = realtime.getJSONObject("weather");
        CurrentWeather currentWeather = handleResponseOfWeather(weather);
        JSONObject wind = realtime.getJSONObject("wind");
        CurrentWind currentWind = handleResponseOfWind(wind);
        return new RealtimeWeather(cityCode,cityName,date,
                time,week,moon,dataUptime,currentWeather,currentWind);
    }

    private static CurrentWeather handleResponseOfWeather(JSONObject weather) throws JSONException{
        String temperature = weather.getString("temperature");
        String humidity = weather.getString("humidity");
        String info = weather.getString("info");
        return new CurrentWeather(temperature,humidity,info);
    }

    private static CurrentWind handleResponseOfWind(JSONObject wind) throws JSONException {
        String direct = wind.getString("direct");
        String power = wind.getString("power");
        return new CurrentWind(direct,power);
    }
}
