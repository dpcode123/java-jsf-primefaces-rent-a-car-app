package com.example.rentacarapp.domain.reservation.views;

import com.example.rentacarapp.domain.selectonemenu.SelectOneMenuService;
import com.example.rentacarapp.domain.reservation.command.ReservationCommand;
import com.example.rentacarapp.domain.reservation.ReservationController;
import com.example.rentacarapp.domain.reservation.ReservationDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RequestScope
public class ReservationFormView implements Serializable {

    private final ReservationController reservationController;
    private final SelectOneMenuService selectOneMenuService;

    public ReservationFormView(ReservationController reservationController, SelectOneMenuService selectOneMenuService) {
        this.reservationController = reservationController;
        this.selectOneMenuService = selectOneMenuService;
    }

    private Long reservationId;
    private ReservationDTO existingReservation;
    private ReservationCommand reservationCommand = new ReservationCommand();
    private Map<String, Long> selectOneMenuVehicles = new LinkedHashMap<>();
    private Map<String, Long> selectOneMenuCustomers = new LinkedHashMap<>();

    private FacesContext facesContext = FacesContext.getCurrentInstance();


    @PostConstruct
    public void init() {
        populateSelectMenusWithSelectItems();

        if (getReservationIdFromParams() != null) {
            initializeReservationData(getReservationIdFromParams());
        }
    }

    private void populateSelectMenusWithSelectItems() {selectOneMenuVehicles = selectOneMenuService.getSelectOneMenuVehicles();
        selectOneMenuCustomers = selectOneMenuService.getSelectOneMenuCustomers();
    }

    public String addReservation() {
        reservationController.addReservation(reservationCommand);
        return "reservations?faces-redirect=true";
    }

    public String editReservation() {
        reservationController.editReservation(reservationId, reservationCommand);
        return "reservations?faces-redirect=true";
    }

    public String deleteReservation() {
        reservationController.deleteReservation(reservationId);
        return "reservations?faces-redirect=true";
    }


    private void initializeReservationData(String vehicleIdParam) {
        reservationId = Long.parseLong(vehicleIdParam);
        existingReservation = reservationController.findReservationById(reservationId);
        reservationCommand = mapDTOtoCommand(existingReservation);
    }

    private String getReservationIdFromParams() {
        return facesContext.getExternalContext()
                .getRequestParameterMap()
                .get("reservationId");
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public ReservationDTO getExistingReservation() {
        return existingReservation;
    }

    public void setExistingReservation(ReservationDTO existingReservation) {
        this.existingReservation = existingReservation;
    }

    public ReservationCommand getReservationCommand() {
        return reservationCommand;
    }

    public void setReservationCommand(ReservationCommand reservationCommand) {
        this.reservationCommand = reservationCommand;
    }

    public Map<String, Long> getSelectOneMenuVehicles() {
        return selectOneMenuVehicles;
    }

    public void setSelectOneMenuVehicles(Map<String, Long> selectOneMenuVehicles) {
        this.selectOneMenuVehicles = selectOneMenuVehicles;
    }

    public Map<String, Long> getSelectOneMenuCustomers() {
        return selectOneMenuCustomers;
    }

    public void setSelectOneMenuCustomers(Map<String, Long> selectOneMenuCustomers) {
        this.selectOneMenuCustomers = selectOneMenuCustomers;
    }


    /**
     * Maps ReservationDTO to ReservationCommand.
     */
    private ReservationCommand mapDTOtoCommand(ReservationDTO reservationDTO) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reservationDTO, ReservationCommand.class);
    }
}
