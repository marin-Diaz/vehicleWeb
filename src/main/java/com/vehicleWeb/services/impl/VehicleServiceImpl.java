package com.vehicleWeb.services.impl;

import com.vehicleWeb.exceptions.VehicleException;
import com.vehicleWeb.data.Vehicle;
import com.vehicleWeb.repository.VehicleRepository;
import com.vehicleWeb.services.VehicleService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

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


    @Override
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
    @Override
    public Vehicle findByModel(Integer model) throws VehicleException {
        Vehicle vehicleWithModel = vehicleRepository.findByModel(model);
        if (vehicleWithModel == null) {
            throw new VehicleException("Vehicle model not found, please check the model and try again.");
        }
        return vehicleWithModel;
    }

}