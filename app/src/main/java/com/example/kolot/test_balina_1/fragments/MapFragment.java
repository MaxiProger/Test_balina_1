package com.example.kolot.test_balina_1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kolot.test_balina_1.GPSTracker;
import com.example.kolot.test_balina_1.R;
import com.example.kolot.test_balina_1.dbModel.Images;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by kolot on 15.01.2018.
 */

public class MapFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private List<Images> images;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_maps, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        images = Images.listAll(Images.class);

        for (int i = 0 ; i < images.size(); i ++){
            mMap.addMarker(new MarkerOptions().position(new LatLng(images.get(i).getLat(), images.get(i)
                    .getLon())).icon(BitmapDescriptorFactory.defaultMarker()).title(String.valueOf(images.get(i).getPhotoId())));
        }

        GPSTracker gpsTracker = new GPSTracker(getContext());
        if(gpsTracker.canGetLocation()) {
            LatLng me = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
            mMap.addMarker(new MarkerOptions().position(me).icon(BitmapDescriptorFactory.defaultMarker()).title("I'm here."));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(me));
        }
    }
}
