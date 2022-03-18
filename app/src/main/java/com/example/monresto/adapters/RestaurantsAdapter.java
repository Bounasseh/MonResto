package com.example.monresto.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import com.example.monresto.R;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.monresto.entities.Restaurant;
import com.example.monresto.persistence.MonRestoDbHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestaurantsAdapter extends ArrayAdapter<Restaurant> {

    private MonRestoDbHelper monRestoDbHelper;
    private ArrayList<Restaurant> restaurants;
    private String category;
    private Map<Integer, Integer> map;
    private Double userLatitude;
    private Double userLongitude;
    private Double restaurantLatitude;
    private Double restaurantLongitude;

    private SharedPreferences preferences;

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public RestaurantsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Restaurant> restaurants, String category) {
        super(context, resource, restaurants);

        monRestoDbHelper = new MonRestoDbHelper(context);

        this.restaurants = restaurants;
        this.category = category;
        map = new HashMap();

        preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String userLatitude = preferences.getString("userLatitude", null);
        String userLongitude = preferences.getString("userLongitude", null);

        if (userLatitude != null && userLongitude != null)
        {
            this.userLatitude = Double.valueOf(userLatitude);
            this.userLongitude = Double.valueOf(userLongitude);
        }
    }

    int getImageDrawable(String category)
    {
        switch (category.toLowerCase())
        {
            case "pizza" : return R.drawable.pizza;
            case "fast food" : return R.drawable.fast_food;
            case "tacos" : return R.drawable.tacos;
            case "marocain" : return R.drawable.marocain;
            case "asiatique" : return R.drawable.asiatique;
            case "italien" : return R.drawable.italien;
            default: return 0;
        }
    }

    public static String calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = 0;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        DecimalFormat df = new DecimalFormat(".###");

        return df.format(Math.sqrt(distance) / 1000);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.restaurants_item, parent, false);

        ImageView image = convertView.findViewById(R.id.restaurants_item_imageView);
        image.setImageResource(getImageDrawable(category));

        TextView restaurantName = convertView.findViewById(R.id.restaurants_item_restaurant_name);
        restaurantName.setText(restaurants.get(position).getName());

        if (userLatitude != null && userLongitude != null) {
            Double[] restaurantLocation = monRestoDbHelper.getRestaurantLocation(restaurants.get(position).getLocation_id());
            TextView distance = convertView.findViewById(R.id.restaurants_item_distance);
            distance.setText(calculateDistance(userLatitude, userLongitude, restaurantLocation[0], restaurantLocation[1]) + " Km");
        }

        map.put(position, restaurants.get(position).getId_restaurant());

        return convertView;
    }
}