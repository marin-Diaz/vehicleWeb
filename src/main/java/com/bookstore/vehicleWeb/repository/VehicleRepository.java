package com.bookstore.vehicleWeb.repository;

import com.bookstore.vehicleWeb.data.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    List<Vehicle> findByBrand(String brand);
}
