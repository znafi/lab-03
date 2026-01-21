package com.example.listycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter<City> {

    public CityAdapter(Context context, ArrayList<City> cities) {
        super(context, 0, cities);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        City city = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content, parent, false);
        }

        TextView cityNameText = convertView.findViewById(R.id.city_name_text);
        TextView provinceText = convertView.findViewById(R.id.province_text);

        cityNameText.setText(city.getName());
        provinceText.setText(city.getProvince());

        return convertView;
    }
}
