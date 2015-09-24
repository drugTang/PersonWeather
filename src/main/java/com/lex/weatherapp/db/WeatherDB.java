package com.lex.weatherapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lex.weatherapp.model.City;
import com.lex.weatherapp.model.County;
import com.lex.weatherapp.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/21.
 */
public class WeatherDB {
    public static final String DB_NAME = "weatherApp";
    public static final int VERSION = 1;

    public static final String TABLE_PROVINCE = "province";
    public static final String TABLE_CITY = "city";
    public static final String TABLE_COUNTY = "county";

    private static WeatherDB weatherDB;

    private SQLiteDatabase db;

    private WeatherDB(Context context) {
        WeatherSQLiteOpenHelper helper = new WeatherSQLiteOpenHelper(context,DB_NAME,null,VERSION);
        db = helper.getWritableDatabase();
    }

    public synchronized static WeatherDB getInstance(Context context) {
        if (weatherDB == null) {
            weatherDB = new WeatherDB(context);
        }
        return weatherDB;
    }

    public synchronized void saveProvince(Province province) {
        if(province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name",province.getName());
            values.put("province_code",province.getCode());
            db.insert(TABLE_PROVINCE,null,values);
        }
    }

    public synchronized void saveCity(City city) {
        if(city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name",city.getName());
            values.put("city_code",city.getCode());
            values.put("province_id",city.getProvinceId());
            db.insert(TABLE_CITY,null,values);
        }
    }

    public synchronized void saveCounty(County county) {
        if(county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name",county.getName());
            values.put("county_code",county.getCode());
            values.put("city_id",county.getCityId());
            db.insert(TABLE_COUNTY,null,values);
        }
    }

    public synchronized List<Province> loadProvinces() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query(TABLE_PROVINCE,null,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            } while(cursor.moveToNext());
        }
        return list;
    }

    public synchronized List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query(TABLE_CITY,null,"province_id = ?",
                new String[]{String.valueOf(provinceId)},null,null,null);
        if(cursor.moveToFirst()) {
            do{
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
                list.add(city);
            }while(cursor.moveToNext());
        }
        return list;
    }

    public synchronized List<County> loadCounties(int cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query(TABLE_COUNTY,null,"city_id = ?",
                new String[]{String.valueOf(cityId)},null,null,null);
        if(cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));
                list.add(county);
            } while(cursor.moveToNext());
        }
        return list;
    }
}
