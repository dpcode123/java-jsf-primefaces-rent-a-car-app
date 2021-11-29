package com.example.rentacarapp.domain.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Long removeById(Long id);

    @Query(value = "SELECT * FROM vehicles WHERE id NOT IN " +
                        "(SELECT vehicle_id FROM reservations WHERE " +
                            "(pickup_time BETWEEN ?1 AND ?2) " +
                                "OR " +
                            "(return_time BETWEEN ?1 AND ?2))",
            nativeQuery = true)
    List<Vehicle> findVehiclesAvailableByDateRange(LocalDateTime pickupTime, LocalDateTime returnTime);

}
