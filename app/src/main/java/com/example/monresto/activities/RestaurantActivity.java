package com.example.monresto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.monresto.R;
import com.example.monresto.persistence.MonRestoDbHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestaurantActivity extends AppCompatActivity implements OnMapReadyCallback
{

    private static final int CAMERA_PIC_REQUEST = 1;
    private MonRestoDbHelper monRestoDbHelper = new MonRestoDbHelper(this);
    private int id_restaurant;
    private String phoneNumber;
    Double[] restaurantLocation;

    SharedPreferences preferences;
    private Double userLatitude;
    private Double userLongitude;

    private static final int REQUEST_CALL = 1;

    private GoogleMap mMap;

    @BindView(R.id.call_button)
    Button callButton;

    @OnClick(R.id.call_button)
    void makePhoneCall()
    {
        Uri phoneNumber = Uri.parse("tel:" + this.phoneNumber);

        Intent call = new Intent(Intent.ACTION_CALL, phoneNumber);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else {
            startActivity(call);
        }
    }

    @BindView(R.id.scan_button)
    Button scanButton;

    @OnClick(R.id.scan_button)
    void scanQRCode()
    {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_PIC_REQUEST);
    }

    @BindView(R.id.display_menu_button)
    Button MenuButton;

    @OnClick(R.id.display_menu_button)
    void displayMenu()
    {
        Intent menuIntent = new Intent(this, MenuActivity.class);
        menuIntent.putExtra("id_restaurant", id_restaurant);
        startActivity(menuIntent);
    }

    @BindView(R.id.map_button)
    Button mapButton;

    @OnClick(R.id.map_button)
    void moveToRestaurant()
    {
        onMapReady(mMap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case REQUEST_CALL : {
                if (permissions[0].equalsIgnoreCase(Manifest.permission.CALL_PHONE) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makePhoneCall();
                } else {
                    Toast.makeText(this, "Permission non accordée", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.sign_out :
            {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                preferences.edit().remove("username").commit();
                preferences.edit().remove("password").commit();

                Toast.makeText(this, "Déconnecté", Toast.LENGTH_SHORT).show();
                Intent mainIntent = new Intent(this, MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
                return true;
            }
            case R.id.location :
            {
                if (userLatitude != null && userLongitude != null) {
                    LatLng userLocation = new LatLng(userLatitude, userLongitude);
                    mMap.addMarker(new MarkerOptions().position(userLocation).title("Vous êtes ici !"));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng restaurant = new LatLng(restaurantLocation[0], restaurantLocation[1]);
        mMap.addMarker(new MarkerOptions().position(restaurant).title("Marker in Restaurant"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(restaurant, 15));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ButterKnife.bind(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String userLatitude = preferences.getString("userLatitude", null);
        String userLongitude = preferences.getString("userLongitude", null);

        if (userLatitude != null && userLongitude != null)
        {
            this.userLatitude = Double.valueOf(userLatitude);
            this.userLongitude = Double.valueOf(userLongitude);
        }

        Bundle extras = getIntent().getExtras();
        id_restaurant = extras.getInt("id_restaurant");
        phoneNumber = monRestoDbHelper.getPhoneNumber(id_restaurant);
        restaurantLocation = monRestoDbHelper.getRestaurantLocationById(id_restaurant);

        System.out.println(id_restaurant);
    }
}