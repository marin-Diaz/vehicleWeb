package com.vehicleWeb.controller;

import com.vehicleWeb.data.Vehicle;
import com.vehicleWeb.exceptions.VehicleException;
import com.vehicleWeb.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    private  final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAll() {
        try {
            List<Vehicle> vehicles = vehicleService.findAll();
            return ResponseEntity.ok(vehicles);
        } catch (VehicleException e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping
    public ResponseEntity<Vehicle> save(@RequestBody Vehicle vehicle) {
        try {
            Vehicle savedVehicle = vehicleService.save(vehicle);
            return ResponseEntity.ok(savedVehicle);
        } catch (VehicleException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Vehicle>> findByBrand(@PathVariable String brand) {
        try {
            List<Vehicle> vehicles = vehicleService.findByBrand(brand);
            return ResponseEntity.ok(vehicles);
        } catch (VehicleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
