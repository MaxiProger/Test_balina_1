package com.example.kolot.test_balina_1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kolot.test_balina_1.R;
import com.example.kolot.test_balina_1.adapters.RecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by kolot on 15.01.2018.
 */

public class PhotosFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private ArrayList<String> dates = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photos_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.rView);
        adapter = new RecyclerViewAdapter(init());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<String> init() {
        for(int i =0 ; i <12 ; i++)
        dates.add(String.valueOf(i+1));
        return dates;
    }
}
