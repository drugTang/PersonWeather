package com.lex.weatherapp.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Administrator on 2015/9/23.
 */
public class StringUtil {

    public static String[] stringToArray(String s) {
        String[] result = null;
        if(!TextUtils.isEmpty(s)) {
            String[] array = s.split("\",\"");
            result = new String[array.length];
            if(array != null && array.length > 0) {
                result[0] = array[0].split("\"")[1];
                for(int i = 1;i < array.length - 1;++i) {
                    result[i] = array[i];
                }
                result[array.length - 1] = array[array.length - 1].split("\"")[0];
            }
        }
        return result;
    }

    /**
     * 将形如2015-09-24的日期转化为形如9月24日的日期
     * @param date
     * @return
     */
    public static String numberDateToDate(String date) {
        StringBuilder result = new StringBuilder();
        if(!TextUtils.isEmpty(date)&&date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            String [] array = date.split("-");
            if(array.length == 3) {
                result.append(array[1]+"月"+array[2]+"日");
            }
        }
        return result.toString();
    }

}
