package com.lex.weatherapp.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lex.myapplication.R;
import com.lex.weatherapp.Adapter.FutureWeatherInfoAdapter;
import com.lex.weatherapp.Adapter.IndexInfoAdapter;
import com.lex.weatherapp.model.FutureWeatherInfo;
import com.lex.weatherapp.model.IndexInfo;
import com.lex.weatherapp.util.HttpCallbackListener;
import com.lex.weatherapp.util.HttpUtil;
import com.lex.weatherapp.util.RoatAnimUtil;
import com.lex.weatherapp.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{
    private String cityName = "梅州";
    String address = "http://op.juhe.cn/onebox/weather/query?cityname=" + cityName
            + "&key=f17f7835f2705d3557191fe579a2c67a";

    private List<FutureWeatherInfo> list = new ArrayList<>();

    private List<IndexInfo> indexList = new ArrayList<>();

    private Button refresh;

    private Button showIndex;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        InitRealTimeWeather();
        initFutureWeatherInfo();
        refresh.setOnClickListener(this);
        showIndex.setOnClickListener(this);
    }

    private void initView() {
        refresh = (Button)findViewById(R.id.refresh);
        listView = (ListView)findViewById(R.id.index_listView);
        showIndex = (Button)findViewById(R.id.show_index);
    }

    private void InitRealTimeWeather() {
        queryFromServer();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        TextView tempText = (TextView)findViewById(R.id.tempText);
        tempText.setText(prefs.getString("temperature", "") + "°");
        TextView weatherInfo = (TextView)findViewById(R.id.weather_info);
        weatherInfo.setText(prefs.getString("info", ""));
        TextView windInfo = (TextView)findViewById(R.id.wind_info);
        windInfo.setText(prefs.getString("direct", "") + ", " + "风力" + prefs.getString("power", ""));
        TextView airInfo = (TextView)findViewById(R.id.air_info);
        airInfo.setText("空气质量  "+prefs.getString("quality",""));
        TextView pTime = (TextView)findViewById(R.id.ptime);
        pTime.setText(prefs.getString("dateTime","")+"  发布");
        TextView currentCity = (TextView)findViewById(R.id.city_current);
        currentCity.setText(prefs.getString("cityName", ""));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.refresh:
                InitRealTimeWeather();
                initFutureWeatherInfo();
                RoatAnimUtil.startAnimationIn(v);
                break;
            case R.id.show_index:
                initLifeInfo();
                break;
            default:
                break;
        }

    }

    private void queryFromServer() {
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Utility.handleJsonWithJsonObject(MainActivity.this
                        , response);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(MainActivity.this, "访问服务器出错", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFutureWeatherInfo() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        FutureWeatherInfoAdapter adapter = new FutureWeatherInfoAdapter(this,R.layout.future_list_item,list);
        listView.setAdapter(adapter);
        list.clear();
        for (int i = 0;i < 7;++i) {
            String weatherDate = prefs.getString("weather"+i+"weatherDate","");
            String week = prefs.getString("weather"+i+"weatherWeek","");
            String info = prefs.getString("weather"+i+"day1","");
            String temp1 = prefs.getString("weather"+i+"night2","");
            String temp2 = prefs.getString("weather"+i+"day2","");
            list.add(new FutureWeatherInfo(weatherDate+"  星期"+week,info,temp1+"~"+temp2+"°"));
        }
    }

    private void initLifeInfo() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        IndexInfoAdapter adapter = new IndexInfoAdapter(this,R.layout.index_list_item,indexList);
        listView.setAdapter(adapter);
        indexList.clear();
        String dress0 = prefs.getString("dress0","");
        String dress1 = prefs.getString("dress1","");
        String cold0 = prefs.getString("cold0","");
        String cold1 = prefs.getString("cold1","");
        String airCondition0 = prefs.getString("airCondition0","");
        String airCondition1 = prefs.getString("airCondition1","");
        String pollution0 = prefs.getString("pollution0","");
        String pollution1 = prefs.getString("pollution1","");
        String carWashing0 = prefs.getString("carWashing0","");
        String carWashing1 = prefs.getString("carWashing1","");
        String sport0 = prefs.getString("sport0","");
        String sport1 = prefs.getString("sport1","");
        String ultravioletRay0 = prefs.getString("ultravioletRay0","");
        String ultravioletRay1 = prefs.getString("ultravioletRay1","");
        indexList.add(new IndexInfo(R.drawable.home,"穿衣指数",dress0,R.drawable.air_info));
        indexList.add(new IndexInfo(R.drawable.home,"感冒指数",cold0,R.drawable.air_info));
        indexList.add(new IndexInfo(R.drawable.home,"空调指数",airCondition0,R.drawable.air_info));
        indexList.add(new IndexInfo(R.drawable.home,"污染指数",pollution0,R.drawable.air_info));
        indexList.add(new IndexInfo(R.drawable.home,"洗车指数",carWashing0,R.drawable.air_info));
        indexList.add(new IndexInfo(R.drawable.home,"运动指数",sport0,R.drawable.air_info));
        indexList.add(new IndexInfo(R.drawable.home,"防晒指数",ultravioletRay0,R.drawable.air_info));
    }

}