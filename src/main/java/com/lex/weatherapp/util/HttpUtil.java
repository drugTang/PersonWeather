package com.lex.weatherapp.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2015/9/21.
 */
public class HttpUtil {
    /**
     * connection time out
     */
    public static final int CONNECT_TIME_OUT = 8000;
    /**
     * read time out
     */
    public static final int READ_TIME_OUT = 8000;
    /**
     * request method
     */
    public static final String REQUEST_METHOD = "GET";


    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(CONNECT_TIME_OUT);
                    connection.setReadTimeout(READ_TIME_OUT);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line = "";
                    StringBuilder response = new StringBuilder();
                    while((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if(listener != null) {
                        //回调onFinish()方法
                        listener.onFinish(response.toString());
                    }
                } catch(Exception e) {
                    if (listener != null) {
                        //回调onError()方法
                        listener.onError(e);
                    }
                } finally {
                    if(connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
