package com.example.rentacarapp.domain.reservation.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class SearchReservationsByDateCommand {

    @Pattern(regexp = "pickup|return", message = "Invalid pickup/return value.")
    private String pickupOrReturn;

    @NotNull(message = "Time from is null.")
    private LocalDateTime timePeriodFrom;

    @NotNull(message = "Time to is null.")
    private LocalDateTime timePeriodTo;


    public String getPickupOrReturn() {
        return pickupOrReturn;
    }

    public void setPickupOrReturn(String pickupOrReturn) {
        this.pickupOrReturn = pickupOrReturn;
    }

    public LocalDateTime getTimePeriodFrom() {
        return timePeriodFrom;
    }

    public void setTimePeriodFrom(LocalDateTime timePeriodFrom) {
        this.timePeriodFrom = timePeriodFrom;
    }

    public LocalDateTime getTimePeriodTo() {
        return timePeriodTo;
    }

    public void setTimePeriodTo(LocalDateTime timePeriodTo) {
        this.timePeriodTo = timePeriodTo;
    }

}
