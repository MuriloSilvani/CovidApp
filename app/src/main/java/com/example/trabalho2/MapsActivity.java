package com.example.trabalho2;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    DiagnosticController diagnosticController;

    private LocationManager locationManager;
    private LocationListener listener;

    private double latitude = 0;
    private double longitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle params = getIntent().getExtras();
        final Long user_id = params.getLong("user_id");

        diagnosticController = new DiagnosticController(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (latitude == 0 || longitude == 0 && mMap!= null){
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    LatLng myLocation = new LatLng(latitude, longitude);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
                    mMap.addMarker(new MarkerOptions().position(myLocation).title("Minha localização").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}, 10);
            }
            return;
        }
        locationManager.requestLocationUpdates("gps", 5000, 0, listener);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        List<Diagnostic> diagnostics = diagnosticController.readAll();

        for (Diagnostic diagnostic:diagnostics) {

            LatLng latLng = new LatLng(diagnostic.getLatitude(), diagnostic.getLongitude());
            if(diagnostic.getDiagnostic() <= 10){
                mMap.addMarker(new MarkerOptions().position(latLng).title("Suspeito de nivel "+diagnostic.getDiagnostic()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
            }else if(diagnostic.getDiagnostic() <= 25){
                mMap.addMarker(new MarkerOptions().position(latLng).title("Suspeito de nivel "+diagnostic.getDiagnostic()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            }else{
                mMap.addMarker(new MarkerOptions().position(latLng).title("Suspeito de nivel "+diagnostic.getDiagnostic()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }
        }



//        mMap.addMarker(new MarkerOptions().position(new LatLng(-27.091052,-52.614389)).title("Marker in Sydney"));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(-37.091052,-52.614389)).title("Marker in Sydney"));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(-47.091052,-52.614389)).title("Marker in Sydney"));


        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

    }
}
