package com.example.rentacarapp.domain.reservation;

import com.example.rentacarapp.domain.customer.Customer;
import com.example.rentacarapp.domain.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservationDTO {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private Long id;
    private Customer customer;
    private Vehicle vehicle;
    private LocalDateTime pickupTime;
    private LocalDateTime returnTime;
    private BigDecimal totalPrice;

    public ReservationDTO(Long id, Customer customer, Vehicle vehicle, LocalDateTime pickupTime, LocalDateTime returnTime, BigDecimal totalPrice) {
        this.id = id;
        this.customer = customer;
        this.vehicle = vehicle;
        this.pickupTime = pickupTime;
        this.returnTime = returnTime;
        this.totalPrice = totalPrice;
    }

    public ReservationDTO() {
    }

    public String getFormattedPickupTime() {
        return pickupTime.format(formatter);
    }

    public String getFormattedReturnTime() {
        return returnTime.format(formatter);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
}
