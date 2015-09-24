package com.lex.weatherapp.util;

/**
 * Created by Administrator on 2015/9/21.
 */
public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);
}
