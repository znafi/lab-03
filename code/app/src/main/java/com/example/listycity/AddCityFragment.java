package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    private EditText cityNameEditText;
    private EditText provinceEditText;
    private OnFragmentInteractionListener listener;
    private City cityToEdit;

    public interface OnFragmentInteractionListener {
        void onOkPressed(City city);
    }

    public static AddCityFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_city, null);
        
        cityNameEditText = view.findViewById(R.id.city_name_edittext);
        provinceEditText = view.findViewById(R.id.province_edittext);

        if (getArguments() != null) {
            cityToEdit = (City) getArguments().getSerializable("city");
            if (cityToEdit != null) {
                cityNameEditText.setText(cityToEdit.getName());
                provinceEditText.setText(cityToEdit.getProvince());
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String title = (cityToEdit != null) ? "Edit City" : "Add City";
        
        return builder
                .setView(view)
                .setTitle(title)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", (dialog, which) -> {
                    String cityName = cityNameEditText.getText().toString();
                    String province = provinceEditText.getText().toString();
                    
                    if (cityToEdit != null) {
                        cityToEdit.setName(cityName);
                        cityToEdit.setProvince(province);
                        listener.onOkPressed(cityToEdit);
                    } else {
                        City newCity = new City(cityName, province);
                        listener.onOkPressed(newCity);
                    }
                })
                .create();
    }
}
