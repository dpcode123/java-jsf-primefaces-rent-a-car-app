package com.example.rentacarapp.domain.vehicle.make;

import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VehicleMakeController {

    private final VehicleMakeService vehicleMakeService;

    public VehicleMakeController(VehicleMakeService vehicleMakeService) {
        this.vehicleMakeService = vehicleMakeService;
    }

    public List<VehicleMake> findAllVehicleMakes() {
        return vehicleMakeService.findAllVehicleMakes();
    }


}
