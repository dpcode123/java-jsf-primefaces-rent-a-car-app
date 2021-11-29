package com.example.rentacarapp.domain.reservation;

import com.example.rentacarapp.domain.vehicle.Vehicle;
import com.example.rentacarapp.domain.vehicle.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Long removeById(Long id);

    List<Reservation> findReservationsByVehicleId(Long vehicleId);
    List<Reservation> findReservationsByVehicleVehicleType(VehicleType vehicleType);

    List<Reservation> findReservationByPickupTimeAfterAndPickupTimeBeforeOrderByPickupTime(LocalDateTime timeStart, LocalDateTime timeEnd);
    List<Reservation> findReservationByReturnTimeAfterAndReturnTimeBeforeOrderByReturnTime(LocalDateTime timeStart, LocalDateTime timeEnd);

}
