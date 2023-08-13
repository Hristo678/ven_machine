package com.example.ven_machine.models;

public class Product {


    String name;
    Integer price;

    double quantity;


    public Product( String name, Integer price, double quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public double getQuantity(){
        return this.quantity;
    }

    public void setQuantity(double quantity){
        this.quantity = quantity;
    }
}
