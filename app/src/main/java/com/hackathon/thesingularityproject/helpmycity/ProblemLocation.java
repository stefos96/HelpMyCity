package com.hackathon.thesingularityproject.helpmycity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProblemLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String address;
    private double latitude = 41.087685;
    private double longitude = 23.550286;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
       convertLocationToAddress();

        // Add a marker to the location of the problem
        LatLng problemLocation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(problemLocation).title(address));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(problemLocation, 13));
    }



    private void convertLocationToAddress(){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> locationList;

        try {
            locationList = geocoder.getFromLocation(latitude, longitude, 1);
            address = locationList.get(0).getAddressLine(0);
        }
        catch (IOException e) {
            address = "Address not found";
        }
    }

}
