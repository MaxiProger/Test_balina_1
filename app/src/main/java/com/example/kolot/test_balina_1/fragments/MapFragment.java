package com.example.kolot.test_balina_1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kolot.test_balina_1.R;

/**
 * Created by kolot on 15.01.2018.
 */

public class MapFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        return view;
    }
}
