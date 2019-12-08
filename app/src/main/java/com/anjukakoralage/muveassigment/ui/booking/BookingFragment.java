package com.anjukakoralage.muveassigment.ui.booking;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anjukakoralage.muveassigment.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by  on 07,December,2019
 */
public class BookingFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "BookingFragment";
    private static String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static String COARES_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static int LOCATION_PERMISSION_CODE = 1234;
    private FusedLocationProviderClient fusedLocationProviderClient;

    private boolean locationPermissionGranted = false;
    GoogleMap gMap;

    public BookingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        getLocationPermission();

        return view;
    }

    private void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (locationPermissionGranted){
            getDeviceLocation();
        }
    }

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getContext(),
                    COARES_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationPermissionGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this.getActivity(),
                        permissions,
                        LOCATION_PERMISSION_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this.getActivity(),
                    permissions,
                    LOCATION_PERMISSION_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        locationPermissionGranted = false;

        switch (requestCode){
            case 123:{
                if (grantResults.length > 0){
                    for (int i = 0; i < grantResults.length; i++){
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                            locationPermissionGranted = false;
                            break;
                        }
                    }
                    locationPermissionGranted = true;
                    //initMap
                    initMap();
                }
            }

        }
    }

    private void getDeviceLocation(){

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        try{
            if (locationPermissionGranted){
                Task location = fusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "onComplete: Found Location");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 16);
                        }
                        else {
                            Log.d(TAG, "onComplete: Not Found Location");
                            //toast to show
                        }
                    }
                });
            }

        } catch (SecurityException e){
            Log.d(TAG, "getDeviceLocation: " + e.getMessage());
        }
    }

    private void moveCamera(LatLng latLng, float zoom){
        Log.d(TAG, "moveCamera: Move Camera to " + latLng.latitude + " - " + latLng.longitude);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom ));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
    }
}
