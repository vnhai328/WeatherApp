package com.example.weatherapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddACity extends BottomSheetDialogFragment {
    EditText edtCityName;
    Button btnAddACity;

    public AddACity() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_a_city, container, false);
        edtCityName = view.findViewById(R.id.edtCityName);
        btnAddACity = view.findViewById(R.id.btnAddACity);
        btnAddACity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main)getActivity()).addCity(edtCityName.getText().toString().trim());
                dismiss();
            }
        });
        return view;
    }
}
