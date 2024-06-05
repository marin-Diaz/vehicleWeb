package com.vehicleWeb.services;

import com.vehicleWeb.data.Vehicle;
import com.vehicleWeb.exceptions.VehicleException;
import com.vehicleWeb.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest

class VehicleServiceTest {

    @Autowired
    private VehicleService vehicleService;
    //@MockBean
    @SpyBean
    private VehicleRepository vehicleRepository;

    @Test

    void save() throws VehicleException{
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Toyota");
        vehicle.setModel(2020);
        vehicle.setPrice(20000.0);
        vehicle.setKilometers(15000.0);
        //doReturn(vehicle).when(vehicleRepository).save(vehicle);
        Vehicle savedVehicle = vehicleService.save(vehicle);

        assertNotNull(savedVehicle);
        assertEquals(vehicle.getBrand(), savedVehicle.getBrand());
    }

    @Test
    void findAll() throws VehicleException {
        mockFindAllWithResults();
        List<Vehicle> vehicles = vehicleService.findAll();
        assertNotNull(vehicles);
        assertFalse(vehicles.isEmpty());
    }

    private void mockFindAllWithResults() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Toyota");
        vehicle.setModel(2020);
        vehicle.setPrice(20000.0);
        vehicle.setKilometers(15000.0);
        doReturn(List.of(vehicle)).when(vehicleRepository).findAll();
    }

    @Test
    void findAllError() throws VehicleException {
        doReturn(Collections.emptyList()).when(vehicleRepository).findAll();

        List<Vehicle> vehicles = vehicleService.findAll();

        assertNotNull(vehicles);
        assertFalse(vehicles.isEmpty());
    }

    @Test
    void findByBrand() throws VehicleException {
        String brand = "Toyota";
        List<Vehicle> vehicles = vehicleService.findByBrand(brand);

        assertFalse(vehicles.isEmpty());
        for (Vehicle vehicle : vehicles) {
            assertEquals(brand, vehicle.getBrand());
        }
    }

    @Test
    void findByModel() throws VehicleException {
        Integer model = 2020;

        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Toyota");
        vehicle.setModel(model);
        vehicle.setPrice(20000.0);
        vehicle.setKilometers(15000.0);

        doReturn(vehicle).when(vehicleRepository).findByModel(model);

        Vehicle result = vehicleService.findByModel(model);
        assertNotNull(result, "The vehicle should not be null");
        assertEquals(model, result.getModel(), "The model of the vehicle should match the queried model");
    }

    @Test
    void findByModelNotFound() {
        Integer model = 9999;  // Asumiendo que este modelo no existe

        doReturn(null).when(vehicleRepository).findByModel(model);

        VehicleException exception = assertThrows(VehicleException.class, () -> {
            vehicleService.findByModel(model);
        });

        assertEquals("Vehicle model not found, please check the model and try again.", exception.getMessage());
    }
}