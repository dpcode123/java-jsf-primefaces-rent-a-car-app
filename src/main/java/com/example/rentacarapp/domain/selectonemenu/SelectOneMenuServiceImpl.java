package com.example.rentacarapp.domain.selectonemenu;

import com.example.rentacarapp.domain.customer.CustomerDTO;
import com.example.rentacarapp.domain.customer.CustomerService;
import com.example.rentacarapp.domain.vehicle.VehicleDTO;
import com.example.rentacarapp.domain.vehicle.VehicleService;
import com.example.rentacarapp.domain.vehicle.VehicleType;
import com.example.rentacarapp.domain.vehicle.make.VehicleMake;
import com.example.rentacarapp.domain.vehicle.make.VehicleMakeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SelectOneMenuServiceImpl implements SelectOneMenuService {

    private final CustomerService customerService;
    private final VehicleService vehicleService;
    private final VehicleMakeService vehicleMakeService;

    public SelectOneMenuServiceImpl(CustomerService customerService, VehicleService vehicleService, VehicleMakeService vehicleMakeService) {
        this.customerService = customerService;
        this.vehicleService = vehicleService;
        this.vehicleMakeService = vehicleMakeService;
    }


    @Override
    public Map<String, Long> getSelectOneMenuCustomers() {
        return customerService.findAllCustomers()
                .stream()
                .collect(Collectors.toMap(
                        CustomerDTO::getFullName,
                        CustomerDTO::getId));
    }

    @Override
    public Map<String, Long> getSelectOneMenuVehicles() {
        return vehicleService.findAllVehicles()
                .stream().collect(Collectors.toMap(
                        VehicleDTO::getFullNameWithLicensePlates,
                        VehicleDTO::getId));
    }

    @Override
    public Map<String, Long> getSelectOneMenuVehicleMakes() {
        return vehicleMakeService.findAllVehicleMakes()
                .stream().collect(Collectors.toMap(
                        VehicleMake::getName,
                        VehicleMake::getId));
    }

    @Override
    public Map<String, Integer> getSelectOneMenuVehicleTypes() {
        return Arrays.stream(VehicleType.getValues())
                .collect(Collectors.toMap(
                        VehicleType::name,
                        VehicleType::ordinal));
    }


}
