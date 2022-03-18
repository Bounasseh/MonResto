package com.example.monresto.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.monresto.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.lang.ref.WeakReference;

interface CallBackInterface
{
    void searchForRestaurants(CharSequence search);
    void updateUserLocation(Double userLatitude, Double userLongitude);
}

public class GetUserLocation extends AsyncTask<Void, Void, Void> {

    private Activity activity;
    private WeakReference<Activity> weakActivity;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 1;

    public Double userLatitude;
    public Double userLongitude;

    CallBackInterface callBackInterface;

    SharedPreferences preferences;

    public GetUserLocation(Activity activity) {
        weakActivity = new WeakReference<>(activity);
        this.activity = weakActivity.get();

        callBackInterface = (CallBackInterface) activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        System.out.println("onPreExecute");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        getCurrentLocation();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        TextView search = activity.findViewById(R.id.activity_restaurants_editText);
        callBackInterface.searchForRestaurants(search.getText().toString());
    }

    public Double[] getCurrentLocation()
    {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            System.out.println("A");
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);
        }
        else
        {
            System.out.println("B");
            LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
            {
                System.out.println("B_A");
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Location> task)
                    {
                        System.out.println("B_A_");
                        Location location = task.getResult();
                        if (location != null)
                        {
                            System.out.println("B_A_A");
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            callBackInterface.updateUserLocation(latitude, longitude);
                        }
                        else
                        {
                            System.out.println("B_A_B");
                            LocationRequest locationRequest = new LocationRequest()
                                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                    .setInterval(10000)
                                    .setFastestInterval(1000)
                                    .setNumUpdates(1);

                            LocationCallback locationCallback = new LocationCallback()
                            {
                                @Override
                                public void onLocationResult(LocationResult locationResult)
                                {
                                    System.out.println("B_A_B_*");
                                    Location location = locationResult.getLastLocation();
                                    double latitude = location.getLatitude();
                                    double longitude = location.getLongitude();

                                    callBackInterface.updateUserLocation(latitude, longitude);
                                }
                            };

                            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                            {
                                System.out.println("B_A_B_A");
                                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                            }
                            else
                            {
                                System.out.println("B_A_B_B");
                            }
                        }
                    }
                });
            }
            else
            {
                System.out.println("B_B");
                activity.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        }
        return new Double[]{userLatitude, userLongitude};
    }
}