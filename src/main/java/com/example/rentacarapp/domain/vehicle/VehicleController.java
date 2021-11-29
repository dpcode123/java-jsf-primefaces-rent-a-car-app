package com.example.rentacarapp.domain.vehicle;

import com.example.rentacarapp.domain.vehicle.command.VehicleCommand;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public List<VehicleDTO> findAllVehicles() { return vehicleService.findAllVehicles(); }

    public VehicleDTO findVehicleById(Long vehicleId) {
        return vehicleService.findVehicleById(vehicleId);
    }

    public VehicleDTO addVehicle(VehicleCommand vehicleCommand) {
        return vehicleService.addVehicle(vehicleCommand);
    }

    public VehicleDTO editVehicle(Long vehicleId, VehicleCommand vehicleCommand) {
        return vehicleService.editVehicle(vehicleId, vehicleCommand);
    }

    public Boolean deleteVehicle(Long vehicleId) {
        return vehicleService.deleteVehicle(vehicleId);
    }

    public List<VehicleDTO> findVehiclesAvailableByDateRange(LocalDateTime pickupTime, LocalDateTime returnTime) {
        return vehicleService.findVehiclesAvailableByDateRange(pickupTime, returnTime);
    }
}
