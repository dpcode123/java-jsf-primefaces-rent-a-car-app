package com.example.rentacarapp.domain.reservation.views;

import com.example.rentacarapp.domain.reservation.ReservationController;
import com.example.rentacarapp.domain.reservation.ReservationDTO;
import com.example.rentacarapp.domain.reservation.command.SearchReservationsByDateCommand;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class ReservationsSearchByDateView {

    private final ReservationController reservationController;

    public ReservationsSearchByDateView(ReservationController reservationController) {
        this.reservationController = reservationController;
    }

    private List<ReservationDTO> reservations;
    private final SearchReservationsByDateCommand command = new SearchReservationsByDateCommand();


    @PostConstruct
    public void init() {
        command.setPickupOrReturn("pickup");
    }

    public void updateReservations() {
        loadReservations();
    }

    private void loadReservations() {
        reservations = reservationController.searchReservationsByDate(command);
    }


    public List<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDTO> reservations) {
        this.reservations = reservations;
    }

    public SearchReservationsByDateCommand getCommand() {
        return command;
    }


}
