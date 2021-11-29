package com.example.rentacarapp.domain.reservation;

import com.example.rentacarapp.domain.reservation.command.FilterReservationsCommand;
import com.example.rentacarapp.domain.reservation.command.ReservationCommand;
import com.example.rentacarapp.domain.reservation.command.SearchReservationsByDateCommand;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public List<ReservationDTO> findAllReservations() {
        return reservationService.findAllReservations();
    }

    public ReservationDTO findReservationById(Long reservationId) {
        return reservationService.findReservationById(reservationId);
    }

    public ReservationDTO addReservation(ReservationCommand reservationCommand) {
        return reservationService.addReservation(reservationCommand);
    }

    public ReservationDTO editReservation(Long reservationId, ReservationCommand reservationCommand) {
        return reservationService.editReservation(reservationId, reservationCommand);
    }

    public Boolean deleteReservation(Long reservationId) {
        return reservationService.deleteReservation(reservationId);
    }

    public List<ReservationDTO> findFilteredReservations(FilterReservationsCommand filterReservationsCommand) {
        return reservationService.filterReservations(filterReservationsCommand);
    }

    public List<ReservationDTO> searchReservationsByDate(SearchReservationsByDateCommand command) {
        return reservationService.searchReservationsByDate(command);
    }


}
