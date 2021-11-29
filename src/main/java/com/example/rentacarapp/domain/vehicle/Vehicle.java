package com.example.rentacarapp.domain.vehicle;

import com.example.rentacarapp.domain.reservation.Reservation;
import com.example.rentacarapp.domain.vehicle.make.VehicleMake;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name="vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Vehicle make:
     * - Mercedes, Audi...
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "make_id",
            referencedColumnName = "id"
    )
    private VehicleMake vehicleMake;

    /**
     * Vehicle model name:
     * - S500, A8...
     */
    @Column(name="model_name")
    private String modelName;

    /**
     * Registration/license plates:
     * - ZG1234AB, DU4568CD
     */
    @Column(name="license_plates")
    private String licensePlates;

    /**
     * Vehicle type:
     * Compact/Limo/SUV...
     */
    @Column(name="vehicle_type")
    private VehicleType vehicleType;

    /**
     * Rental price per day
     */
    @Column(name="price_per_day")
    private BigDecimal pricePerDay;

    /**
     * Minimum age of driver for this vehicle
     */
    @Column(name="minimum_drivers_age")
    private Integer minimumDriversAge;

    /**
     * Color code
     */
    @Column(name="color_code")
    private String colorCode;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Reservation> reservations;

    public Vehicle(Long id, VehicleMake vehicleMake, String modelName, String licensePlates, VehicleType vehicleType, BigDecimal pricePerDay, Integer minimumDriversAge, String colorCode) {
        this.id = id;
        this.vehicleMake = vehicleMake;
        this.modelName = modelName;
        this.licensePlates = licensePlates;
        this.vehicleType = vehicleType;
        this.pricePerDay = pricePerDay;
        this.minimumDriversAge = minimumDriversAge;
        this.colorCode = colorCode;
    }

    public Vehicle(VehicleMake make, String modelName, String licensePlates, VehicleType vehicleType, BigDecimal pricePerDay, Integer minimumDriversAge, String colorCode) {
        this.vehicleMake = make;
        this.modelName = modelName;
        this.licensePlates = licensePlates;
        this.vehicleType = vehicleType;
        this.pricePerDay = pricePerDay;
        this.minimumDriversAge = minimumDriversAge;
        this.colorCode = colorCode;
    }

    public Vehicle() {
    }


    public String getFullNameWithLicensePlates() {
        return this.vehicleMake.getName() +
                " " + this.modelName +
                " (" + this.licensePlates + ")";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleMake getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(VehicleMake vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Integer getMinimumDriversAge() {
        return minimumDriversAge;
    }

    public void setMinimumDriversAge(Integer minimumDriversAge) {
        this.minimumDriversAge = minimumDriversAge;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }




}
