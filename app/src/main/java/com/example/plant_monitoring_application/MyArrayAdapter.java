package com.example.plant_monitoring_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<MyDataModel> {

    List<MyDataModel> modelList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public MyArrayAdapter(Context context, List<MyDataModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }

    @Override
    public MyDataModel getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        MyDataModel item = getItem(position);

        vh.textViewName.setText( "Temperature "+item.getTemprature()+"C'" );
        vh.textViewCountry.setText("Humidity "+item.getHumidity()+"%" );
        vh.textViewMoisture.setText( "Moisture "+item.getMoisture()+"%");
        vh.textViewRain.setText("Rain "+item.getRain()+"%");
        vh.textViewLight_intencity.setText("light_intencity "+ item.getLight_intencity()+"%");
        vh.textViewDate.setText("Date "+item.getDate());
        vh.textViewTime.setText("Time "+item.getTime());



          System.out.println(vh.textViewCountry);
        System.out.println(vh.textViewName);


        return vh.rootView;
    }

    /**
     * ViewHolder class for layout.<br />
     * <br />
     * Auto-created on 2016-01-05 00:50:26 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private static class ViewHolder {
        public final RelativeLayout rootView;

        public final TextView textViewName;
        public final TextView textViewCountry;
        public final TextView textViewMoisture;
        public final TextView textViewRain;
        public final TextView textViewLight_intencity;
        public  final TextView textViewDate;
        public final TextView textViewTime;


        private ViewHolder(RelativeLayout rootView, TextView textViewName, TextView textViewCountry, TextView textViewMoisture, TextView textViewRain, TextView textViewLight_intencity,  TextView textViewDate,  TextView textViewTime) {
            this.rootView = rootView;
            this.textViewName = textViewName;
            this.textViewCountry = textViewCountry;
            this.textViewMoisture = textViewMoisture;
            this.textViewRain = textViewRain;
            this.textViewLight_intencity = textViewLight_intencity;
            this.textViewDate = textViewDate;
            this.textViewTime = textViewTime;

        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewCountry = (TextView) rootView.findViewById(R.id.textViewCountry);
            TextView textViewMoisture = (TextView) rootView.findViewById(R.id.textViewMoisture);
            TextView textViewRain = (TextView) rootView.findViewById(R.id.textViewRain);
            TextView textViewLight_intencity = (TextView) rootView.findViewById(R.id.textViewLight);
            TextView textViewDate = (TextView) rootView.findViewById(R.id.textViewDate);
            TextView textViewTime = (TextView) rootView.findViewById(R.id.textViewTime);

            return new ViewHolder(rootView, textViewName, textViewCountry, textViewMoisture, textViewRain, textViewLight_intencity, textViewDate, textViewTime);
        }
    }
}
