package com.example.rentacarapp.domain.vehicle.command;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class VehicleCommand {

    @NotNull(message = "Vehicle make is empty.")
    private Long vehicleMakeId;

    @NotBlank(message = "Model name is empty.")
    private String modelName;

    private String licensePlates;

    @NotNull(message = "Vehicle make is empty.")
    private Integer vehicleTypeId;

    private BigDecimal pricePerDay;

    @Min(value = 18, message = "Age should be at least 18.")
    private Integer minimumDriversAge;

    private String colorCode;


    public VehicleCommand(Long vehicleMakeId, String modelName, String licensePlates, Integer vehicleTypeId, BigDecimal pricePerDay, Integer minimumDriversAge, String colorCode) {
        this.vehicleMakeId = vehicleMakeId;
        this.modelName = modelName;
        this.licensePlates = licensePlates;
        this.vehicleTypeId = vehicleTypeId;
        this.pricePerDay = pricePerDay;
        this.minimumDriversAge = minimumDriversAge;
        this.colorCode = colorCode;
    }

    public VehicleCommand() {
    }

    public Long getVehicleMakeId() {
        return vehicleMakeId;
    }

    public void setVehicleMakeId(Long vehicleMakeId) {
        this.vehicleMakeId = vehicleMakeId;
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

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
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
}
