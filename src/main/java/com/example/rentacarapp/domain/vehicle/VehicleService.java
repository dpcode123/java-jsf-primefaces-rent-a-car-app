package com.example.rentacarapp.domain.vehicle;

import com.example.rentacarapp.domain.vehicle.command.VehicleCommand;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleService {
    List<VehicleDTO> findAllVehicles();

    VehicleDTO findVehicleById(Long vehicleId);

    VehicleDTO addVehicle(VehicleCommand vehicleCommand);

    VehicleDTO editVehicle(Long vehicleId, VehicleCommand vehicleCommand);

    Boolean deleteVehicle(Long vehicleId);

    List<VehicleDTO> findVehiclesAvailableByDateRange(LocalDateTime pickupTime, LocalDateTime returnTime);
}
