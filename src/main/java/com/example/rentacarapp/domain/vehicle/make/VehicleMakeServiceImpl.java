package com.example.rentacarapp.domain.vehicle.make;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleMakeServiceImpl implements VehicleMakeService {

    private final VehicleMakeRepository vehicleMakeRepository;

    public VehicleMakeServiceImpl(VehicleMakeRepository vehicleMakeRepository) {
        this.vehicleMakeRepository = vehicleMakeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleMake> findAllVehicleMakes() {
        return new ArrayList<>(vehicleMakeRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleMake findVehicleMakeById(Long vehicleMakeId) {
        return vehicleMakeRepository.findById(vehicleMakeId)
                .orElseThrow(() -> new RuntimeException("Vehicle make not found."));
    }
}
