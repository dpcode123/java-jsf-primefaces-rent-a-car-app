package com.example.rentacarapp.domain.vehicle.make;

import java.util.List;

public interface VehicleMakeService {
    List<VehicleMake> findAllVehicleMakes();
    VehicleMake findVehicleMakeById(Long vehicleMakeId);
}
