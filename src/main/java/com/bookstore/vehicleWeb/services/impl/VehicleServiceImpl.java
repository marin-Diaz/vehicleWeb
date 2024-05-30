package com.bookstore.vehicleWeb.services.impl;

import com.bookstore.vehicleWeb.exceptions.VehicleExceptions;
import com.bookstore.vehicleWeb.data.Vehicle;
import com.bookstore.vehicleWeb.repository.VehicleRepository;
import com.bookstore.vehicleWeb.services.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl  implements VehicleService {

    private final VehicleRepository vehicleRepository ;

    public VehicleServiceImpl( VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;

    }

    @Override
    public List<Vehicle> findAll() throws VehicleExceptions {
        var vehicles = vehicleRepository.findAll();

        if (vehicles.isEmpty()) {
            throw new VehicleExceptions(("No vehicles found"));
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> findByBrand(String brand) {
        return vehicleRepository.findByBrand(brand);
    }
    public Vehicle save(Vehicle vehicle) throws VehicleExceptions {

        List<Vehicle> vehicles = vehicleRepository.findAll();
        if (vehicles.size() >= 15) {
            throw new VehicleExceptions("Vehicle can't be added, limit reached");
        }
        try {
            return vehicleRepository.save(vehicle);
        } catch (Exception e) {
            throw new VehicleExceptions("Error saving vehicle: " + e.getMessage());
        }

    }
}
