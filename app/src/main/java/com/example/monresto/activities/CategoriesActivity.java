package com.example.monresto.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.monresto.R;
import com.example.monresto.adapters.CategoriesAdapter;
import com.example.monresto.persistence.MonRestoDbHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class CategoriesActivity extends AppCompatActivity {

    MonRestoDbHelper monRestoDbHelper = new MonRestoDbHelper(this);

    @BindView(R.id.activity_categories_gridView)
    GridView gridView;

    @OnItemClick(R.id.activity_categories_gridView)
    public void onCategoryItemClick(View view)
    {
        TextView text = view.findViewById(R.id.list_item_textView);
        String category = text.getText().toString();
        Intent restaurantsIntent = new Intent(this, RestaurantsActivity.class);
        restaurantsIntent.putExtra("category", category);
        startActivity(restaurantsIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_out, menu);
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);

        // récupérer la liste des catégories
        ArrayList<String> categories = monRestoDbHelper.read_categories();

        CategoriesAdapter adapter = new CategoriesAdapter(this, R.layout.categories_item, categories);
        gridView.setAdapter(adapter);

        System.out.println("*** # finish # ***");
    }
}