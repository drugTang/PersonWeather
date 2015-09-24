package com.lex.weatherapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lex.myapplication.R;
import com.lex.weatherapp.model.IndexInfo;

import java.util.List;

/**
 * Created by Administrator on 2015/9/24.
 */
public class IndexInfoAdapter extends ArrayAdapter<IndexInfo> {
    private int resourceId;
    public IndexInfoAdapter(Context context, int resource, List<IndexInfo> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IndexInfo indexInfo = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.iconImage = (ImageView)view.findViewById(R.id.icon_image);
            viewHolder.titleText = (TextView)view.findViewById(R.id.title_text);
            viewHolder.simpleText = (TextView)view.findViewById(R.id.simple_text);
            viewHolder.arrowImage = (ImageView)view.findViewById(R.id.arrow_image);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.iconImage.setImageResource(indexInfo.getIconId());
        viewHolder.titleText.setText(indexInfo.getTitleText());
        viewHolder.simpleText.setText(indexInfo.getSimpleText());
        viewHolder.arrowImage.setImageResource(indexInfo.getArrowId());
        return view;
    }

    class ViewHolder {
        ImageView iconImage;
        TextView titleText;
        TextView simpleText;
        ImageView arrowImage;
    }
}
