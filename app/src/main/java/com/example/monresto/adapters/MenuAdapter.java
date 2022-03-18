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
import com.example.monresto.entities.Item;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<Item> {

    ArrayList<Item> items;

    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Item> items) {
        super(context, resource, items);
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.menu_item, parent, false);

        TextView itemName = convertView.findViewById(R.id.menu_item_name);
        itemName.setText(items.get(position).getName());
        TextView itemPrice = convertView.findViewById(R.id.menu_item_price);
        itemPrice.setText(items.get(position).getPrice() + " DH");

        return convertView;
    }
}
