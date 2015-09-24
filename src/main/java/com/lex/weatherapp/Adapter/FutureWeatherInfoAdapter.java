package com.lex.weatherapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lex.myapplication.R;
import com.lex.weatherapp.model.FutureWeatherInfo;

import java.util.List;

/**
 * Created by Administrator on 2015/9/22.
 */
public class FutureWeatherInfoAdapter extends ArrayAdapter<FutureWeatherInfo> {
    private int resourceId;

    public FutureWeatherInfoAdapter(Context context, int resource, List<FutureWeatherInfo> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FutureWeatherInfo futureWeatherInfo = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.dateTimeText = (TextView)view.findViewById(R.id.dateTimeText);
            viewHolder.weatherInfoText = (TextView)view.findViewById(R.id.weatherinfoText);
            viewHolder.temperateText = (TextView)view.findViewById(R.id.temperateText);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.dateTimeText.setText(futureWeatherInfo.getDateTime());
        viewHolder.weatherInfoText.setText(futureWeatherInfo.getWeatherInfo());
        viewHolder.temperateText.setText(futureWeatherInfo.getTemperate());
        return view;
    }

    class ViewHolder {
        TextView dateTimeText;

        TextView weatherInfoText;

        TextView temperateText;
    }
}
