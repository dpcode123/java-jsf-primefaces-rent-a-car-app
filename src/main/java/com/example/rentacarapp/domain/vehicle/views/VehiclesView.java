package com.example.rentacarapp.domain.vehicle.views;

import com.example.rentacarapp.domain.vehicle.VehicleController;
import com.example.rentacarapp.domain.vehicle.VehicleDTO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class VehiclesView {

    private final VehicleController vehicleController;

    public VehiclesView(VehicleController vehicleController) {
        this.vehicleController = vehicleController;
    }

    private List<VehicleDTO> vehicles;

    @PostConstruct
    public void init() { vehicles = vehicleController.findAllVehicles(); }

    public List<VehicleDTO> getVehicles() { return vehicles; }
}
