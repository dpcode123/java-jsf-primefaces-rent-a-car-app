package com.example.rentacarapp.domain.selectonemenu;

import java.util.Map;

public interface SelectOneMenuService {
    Map<String, Long> getSelectOneMenuCustomers();
    Map<String, Long> getSelectOneMenuVehicles();
    Map<String, Long> getSelectOneMenuVehicleMakes();
    Map<String, Integer> getSelectOneMenuVehicleTypes();
}
