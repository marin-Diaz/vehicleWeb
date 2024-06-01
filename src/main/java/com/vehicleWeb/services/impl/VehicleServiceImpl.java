package com.vehicleWeb.services.impl;

import com.vehicleWeb.data.User;
import com.vehicleWeb.exceptions.UserException;
import com.vehicleWeb.exceptions.VehicleException;
import com.vehicleWeb.data.Vehicle;
import com.vehicleWeb.repository.VehicleRepository;
import com.vehicleWeb.services.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl  implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;

    }

    @Override
    public List<Vehicle> findAll() throws VehicleException {
        var vehicles = vehicleRepository.findAll();

        if (vehicles.isEmpty()) {
            throw new VehicleException(("No vehicles found"));
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> findByBrand(String brand) throws VehicleException {
        List<Vehicle> vehiclesWithBrand = vehicleRepository.findByBrand(brand);
        if (vehiclesWithBrand.isEmpty()) {
            throw new VehicleException("Brand not found, please check the brand and try again.");
        }
        return vehiclesWithBrand;

    }

    public Vehicle save(Vehicle vehicle) throws VehicleException {

        List<Vehicle> vehicles = vehicleRepository.findAll();
        if (vehicles.size() >= 15) {
            throw new VehicleException("Vehicle can't be added, limit reached");
        }
        try {
            return vehicleRepository.save(vehicle);
        } catch (Exception e) {
            throw new VehicleException("Error saving vehicle: " + e.getMessage());
        }

    }

}