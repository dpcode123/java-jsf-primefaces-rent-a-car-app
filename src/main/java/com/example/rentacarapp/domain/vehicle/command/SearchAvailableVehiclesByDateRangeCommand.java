package com.example.rentacarapp.domain.vehicle.command;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class SearchAvailableVehiclesByDateRangeCommand {

    @NotNull(message = "Pickup time is null.")
    private LocalDateTime pickupTime;

    @NotNull(message = "Return time is null.")
    private LocalDateTime returnTime;


    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }
}
