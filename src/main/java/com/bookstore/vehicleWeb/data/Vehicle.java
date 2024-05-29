package com.bookstore.vehicleWeb.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;

    private String brand;
    private Integer model;
    private Double price;
    private Double Kilometers;

    public Vehicle(String brand, Integer model, Double price, Double kilometers) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        Kilometers = kilometers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {return brand;}

    public void setBrand(String branch) {this.brand = branch;}

    public Integer getModel() {return model;}

    public void setModel(Integer model) {this.model = model;}

    public Double getPrice() {return price;}

    public void setPrice(Double price) {this.price = price;}

    public Double getKilometers() {return Kilometers;}

    public void setKilometers(Double kilometers) {Kilometers = kilometers;}


}
