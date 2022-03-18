package com.example.monresto.entities;

public class Restaurant {
    private int id_restaurant;
    private String name;
    private String phone;
    private int category_id;
    private int location_id;

    public Restaurant() {
    }

    public Restaurant(int id_restaurant, String name, String phone, int category_id, int location_id) {
        this.id_restaurant = id_restaurant;
        this.name = name;
        this.phone = phone;
        this.category_id = category_id;
        this.location_id = location_id;
    }

    public int getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id_restaurant=" + id_restaurant +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", category_id=" + category_id +
                ", location_id=" + location_id +
                '}';
    }
}
