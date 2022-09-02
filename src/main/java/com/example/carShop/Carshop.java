package com.example.carShop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Carshop {

    private int code;
    private String name;
    private String availability;
    private String price;
    private Boolean isDeleted = Boolean.FALSE;
    private String color;
    private String country;
}