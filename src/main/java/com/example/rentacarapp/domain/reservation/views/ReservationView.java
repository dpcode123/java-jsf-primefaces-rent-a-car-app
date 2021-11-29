package com.example.rentacarapp.domain.reservation.views;

import com.example.rentacarapp.domain.reservation.ReservationController;
import com.example.rentacarapp.domain.reservation.ReservationDTO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@Component
@ViewScoped
public class ReservationView {

    private final ReservationController reservationController;

    public ReservationView(ReservationController reservationController) {
        this.reservationController = reservationController;
    }

    private ReservationDTO reservation;

    private FacesContext facesContext = FacesContext.getCurrentInstance();

    @PostConstruct
    public void init() {
        if (getReservationIdFromParams() != null) {
            initializeReservationData(getReservationIdFromParams());
        }
    }

    private void initializeReservationData(String reservationIdParam) {
        reservation = reservationController
                .findReservationById(Long.parseLong(reservationIdParam));
    }

    private String getReservationIdFromParams() {
        return facesContext.getExternalContext()
                .getRequestParameterMap()
                .get("reservationId");
    }

    public ReservationDTO getReservation() {
        return reservation;
    }

}
