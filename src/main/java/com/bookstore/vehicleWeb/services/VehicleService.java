package com.bookstore.vehicleWeb.services;

import com.bookstore.vehicleWeb.data.Vehicle;
import com.bookstore.vehicleWeb.exceptions.VehicleExceptions;

import java.util.List;

public interface VehicleService {

    List<Vehicle> findAll() throws Exception;

    Vehicle save(Vehicle vehicle) throws VehicleExceptions;


    List<Vehicle> findByBrand(String brand);


}
