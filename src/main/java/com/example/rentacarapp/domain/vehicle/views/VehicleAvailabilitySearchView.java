package com.example.rentacarapp.domain.vehicle.views;

import com.example.rentacarapp.domain.vehicle.VehicleController;
import com.example.rentacarapp.domain.vehicle.VehicleDTO;
import com.example.rentacarapp.domain.vehicle.command.SearchAvailableVehiclesByDateRangeCommand;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class VehicleAvailabilitySearchView implements Serializable {

    private final VehicleController vehicleController;

    public VehicleAvailabilitySearchView(VehicleController vehicleController) {
        this.vehicleController = vehicleController;
    }

    private List<VehicleDTO> availableVehicles;
    private final SearchAvailableVehiclesByDateRangeCommand command = new SearchAvailableVehiclesByDateRangeCommand();

    public void updateAvailableVehicles() {
        loadAvailableVehicles();
    }

    private void loadAvailableVehicles() {
        availableVehicles = vehicleController.findVehiclesAvailableByDateRange(command.getPickupTime(), command.getReturnTime());
    }

    public List<VehicleDTO> getAvailableVehicles() {
        return availableVehicles;
    }

    public void setAvailableVehicles(List<VehicleDTO> availableVehicles) {
        this.availableVehicles = availableVehicles;
    }

    public SearchAvailableVehiclesByDateRangeCommand getCommand() {
        return command;
    }
}
