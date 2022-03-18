package com.example.monresto.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.monresto.R;

import java.util.ArrayList;

public class CategoriesAdapter extends ArrayAdapter<String> {

    ArrayList<String> categories;

    public CategoriesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> categories) {
        super(context, resource, categories);
        this.categories = categories;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.categories_item, parent, false);

        TextView textView = convertView.findViewById(R.id.list_item_textView);
        textView.setText(categories.get(position));

        return convertView;
    }
}