package com.vehicleWeb.services;

import com.vehicleWeb.exceptions.VehicleException;
import com.vehicleWeb.data.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> findAll() throws VehicleException;

    Vehicle save(Vehicle vehicle) throws VehicleException;


    List<Vehicle> findByBrand(String brand)throws VehicleException;


    Vehicle findByModel(Integer model) throws VehicleException;
}
