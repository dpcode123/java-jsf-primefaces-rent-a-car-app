package com.example.rentacarapp.domain.vehicle.make;

import com.example.rentacarapp.domain.vehicle.Vehicle;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="vehicle_makes")
public class VehicleMake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "vehicleMake", fetch = FetchType.LAZY)
    private Set<Vehicle> vehicles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
