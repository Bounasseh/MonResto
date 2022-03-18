package com.example.monresto.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.monresto.R;
import com.example.monresto.adapters.RestaurantsAdapter;
import com.example.monresto.entities.Restaurant;
import com.example.monresto.persistence.MonRestoDbHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;

public class RestaurantsActivity extends AppCompatActivity implements CallBackInterface {

    private MonRestoDbHelper monRestoDbHelper = new MonRestoDbHelper(this);
    private String category;
    private RestaurantsAdapter adapter;
    private static final int REQUEST_CODE = 1;

    private Double userLatitude;
    private Double userLongitude;

    SharedPreferences preferences;

    @BindView(R.id.activity_restaurants_gridView)
    GridView gridView;

    @BindView(R.id.activity_restaurants_editText)
    EditText editText;

    @OnTextChanged(R.id.activity_restaurants_editText)
    public void searchForRestaurants(CharSequence search)
    {
        ArrayList<Restaurant> restaurants = monRestoDbHelper.read_restaurants(search.toString(), category);
        adapter = new RestaurantsAdapter(this, R.id.restaurants_item_restaurant_name, restaurants, category);
        gridView.setAdapter(adapter);
    }

    @Override
    public void updateUserLocation(Double userLatitude, Double userLongitude) {
        System.out.println("Update with " + userLatitude + userLongitude);

        String userLatitudeStr = String.valueOf(userLatitude);
        String userLongitudeStr = String.valueOf(userLongitude);

        preferences.edit().putString("userLatitude", userLatitudeStr).commit();
        preferences.edit().putString("userLongitude", userLongitudeStr).commit();

        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;

        editText.setText(editText.getText().toString());
        editText.setSelection(editText.getText().length());

        System.out.println("this :" + this.userLatitude + this.userLongitude);
    }

    @OnItemClick(R.id.activity_restaurants_gridView)
    public void onRestaurantItemClick(int position)
    {
        int id_restaurant = adapter.getMap().get(position);
        Intent restaurantIntent = new Intent(this, RestaurantActivity.class);
        restaurantIntent.putExtra("id_restaurant", id_restaurant);
        startActivity(restaurantIntent);
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
                System.out.println("menu -> location");
                GetUserLocation getUserLocation = new GetUserLocation(this);
                getUserLocation.execute();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case REQUEST_CODE:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    System.out.println("C_A");
                    GetUserLocation getUserLocation = new GetUserLocation(this);
                    getUserLocation.execute();
                }
                else
                {
                    System.out.println("C_B");
                    Toast.makeText(this, "Permission non accordée", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String userLatitude = preferences.getString("userLatitude", null);
        String userLongitude = preferences.getString("userLongitude", null);

        if (userLatitude != null && userLongitude != null)
        {
            System.out.println("if : " + userLatitude + userLongitude);
            this.userLatitude = Double.valueOf(userLatitude);
            this.userLongitude = Double.valueOf(userLongitude);
            System.out.println("if : " + this.userLatitude + this.userLongitude);
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // to prevent the keyboard from displaying on activity start
        Bundle extras = getIntent().getExtras();
        category = extras.getString("category");

        searchForRestaurants("");
    }
}