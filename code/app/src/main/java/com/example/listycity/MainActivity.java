package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = new ArrayList<>();
        dataList.add(new City("Edmonton", "Alberta"));
        dataList.add(new City("Vancouver", "British Columbia"));
        dataList.add(new City("Moscow", "Russia"));
        dataList.add(new City("Sydney", "Australia"));
        dataList.add(new City("Berlin", "Germany"));
        dataList.add(new City("Vienna", "Austria"));
        dataList.add(new City("Tokyo", "Japan"));
        dataList.add(new City("Beijing", "China"));
        dataList.add(new City("Osaka", "Japan"));
        dataList.add(new City("New Delhi", "India"));

        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        FloatingActionButton addCityButton = findViewById(R.id.add_city_button);
        addCityButton.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY");
        });

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City cityToEdit = dataList.get(position);
            AddCityFragment fragment = AddCityFragment.newInstance(cityToEdit);
            fragment.show(getSupportFragmentManager(), "EDIT_CITY");
        });
    }

    @Override
    public void onOkPressed(City city) {
        if (!dataList.contains(city)) {
            dataList.add(city);
        }
        cityAdapter.notifyDataSetChanged();
    }
}