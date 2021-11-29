package com.example.rentacarapp.domain.reservation;

import com.example.rentacarapp.domain.customer.Customer;
import com.example.rentacarapp.domain.vehicle.Vehicle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name="reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id"
    )
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "vehicle_id",
            referencedColumnName = "id"
    )
    private Vehicle vehicle;

    private LocalDateTime pickupTime;

    private LocalDateTime returnTime;

    private BigDecimal totalPrice;


    public Reservation(Long id, Customer customer, Vehicle vehicle, LocalDateTime pickupTime, LocalDateTime returnTime, BigDecimal totalPrice) {
        this.id = id;
        this.customer = customer;
        this.vehicle = vehicle;
        this.pickupTime = pickupTime;
        this.returnTime = returnTime;
        this.totalPrice = totalPrice;
    }

    public Reservation(Customer customer, Vehicle vehicle, LocalDateTime pickupTime, LocalDateTime returnTime, BigDecimal totalPrice) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.pickupTime = pickupTime;
        this.returnTime = returnTime;
        this.totalPrice = totalPrice;
    }

    public Reservation() {
    }

    public BigDecimal calculateTotalPrice() {
        Duration duration = Duration.between(this.pickupTime, this.returnTime);
        long days = duration.toDays() + 1;

        return this.vehicle.getPricePerDay().multiply(BigDecimal.valueOf(days));
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



    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customer=" + customer +
                ", vehicle=" + vehicle +
                ", pickupTime=" + pickupTime +
                ", returnTime=" + returnTime +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
