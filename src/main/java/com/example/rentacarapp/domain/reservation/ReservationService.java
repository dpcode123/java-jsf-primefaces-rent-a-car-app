package com.example.rentacarapp.domain.reservation;

import com.example.rentacarapp.domain.reservation.command.FilterReservationsCommand;
import com.example.rentacarapp.domain.reservation.command.ReservationCommand;
import com.example.rentacarapp.domain.reservation.command.SearchReservationsByDateCommand;

import java.util.List;

public interface ReservationService {

    List<ReservationDTO> findAllReservations();

    ReservationDTO findReservationById(Long reservationId);

    ReservationDTO addReservation(ReservationCommand reservationCommand);

    ReservationDTO editReservation(Long reservationId, ReservationCommand reservationCommand);

    Boolean deleteReservation(Long reservationId);

    List<ReservationDTO> findReservationsByVehicle(Long vehicleId);
    List<ReservationDTO> findReservationsByVehicleType(Integer vehicleTypeId);

    List<ReservationDTO> filterReservations(FilterReservationsCommand command);

    List<ReservationDTO> searchReservationsByDate(SearchReservationsByDateCommand command);
}
