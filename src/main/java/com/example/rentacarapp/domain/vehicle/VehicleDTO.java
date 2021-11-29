package com.example.rentacarapp.domain.vehicle;

import com.example.rentacarapp.domain.vehicle.make.VehicleMake;
import java.math.BigDecimal;

public class VehicleDTO {

    private Long id;
    private VehicleMake vehicleMake;
    private String modelName;
    private String licensePlates;
    private VehicleType vehicleType;
    private BigDecimal pricePerDay;
    private Integer minimumDriversAge;
    private String colorCode;

    public VehicleDTO(Long id, VehicleMake vehicleMake, String modelName, String licensePlates, VehicleType vehicleType, BigDecimal pricePerDay, Integer minimumDriversAge, String colorCode) {
        this.id = id;
        this.vehicleMake = vehicleMake;
        this.modelName = modelName;
        this.licensePlates = licensePlates;
        this.vehicleType = vehicleType;
        this.pricePerDay = pricePerDay;
        this.minimumDriversAge = minimumDriversAge;
        this.colorCode = colorCode;
    }

    public VehicleDTO() {
    }


    public String getColorCodeWithHashTag() {
        return "#"+colorCode;
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

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "id=" + id +
                ", vehicleMake=" + vehicleMake +
                ", modelName='" + modelName + '\'' +
                ", licensePlates='" + licensePlates + '\'' +
                ", vehicleType=" + vehicleType +
                ", pricePerDay=" + pricePerDay +
                ", minimumDriversAge=" + minimumDriversAge +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }


}
