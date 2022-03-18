package com.example.monresto.entities;

public class Item {
    private int item_id;
    private String name;
    private String price;
    private int restaurant_id;

    public Item(int item_id, String name, String price, int restaurant_id) {
        this.item_id = item_id;
        this.name = name;
        this.price = price;
        this.restaurant_id = restaurant_id;
    }

    public Item() {
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
