package com.example.kolot.test_balina_1;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.kolot.test_balina_1.dbModel.Images;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GoogleMap map;
    private List<Images> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        images = Images.listAll(Images.class);

        for (int i = 0 ; i < images.size(); i ++){
            //File img = new File();
            mMap.addMarker(new MarkerOptions().position(new LatLng(images.get(i).getLat(), images.get(i).getLon())));
        }

        GPSTracker gpsTracker = new GPSTracker(this);
        if(gpsTracker.canGetLocation()) {
            LatLng me = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
            mMap.addMarker(new MarkerOptions().position(me));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(me));
        }
    }
}
