package com.example.demo;

public class CarBase {

    private int number;
    private String brand;
    private String model;
    private String color;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "CarBase{" +
                "number=" + number +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", model='" + model + '\'' +
               '}';
    }
}
