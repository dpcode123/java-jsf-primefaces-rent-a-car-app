package com.example.rentacarapp.domain.reservation.command;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservationCommand {

    private Long customerId;
    private Long vehicleId;
    private LocalDateTime pickupTime;
    private LocalDateTime returnTime;
    private BigDecimal totalPrice;

    public ReservationCommand(Long customerId, Long vehicleId, LocalDateTime pickupTime, LocalDateTime returnTime, BigDecimal totalPrice) {
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.pickupTime = pickupTime;
        this.returnTime = returnTime;
        this.totalPrice = totalPrice;
    }

    public ReservationCommand() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ReservationCommand{" +
                "customerId=" + customerId +
                ", vehicleId=" + vehicleId +
                ", pickupTime=" + pickupTime +
                ", returnTime=" + returnTime +
                ", totalPrice=" + totalPrice +
                '}';
    }

}
