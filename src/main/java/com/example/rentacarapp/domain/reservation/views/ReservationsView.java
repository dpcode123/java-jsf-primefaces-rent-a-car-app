package com.example.rentacarapp.domain.reservation.views;

import com.example.rentacarapp.domain.selectonemenu.SelectOneMenuService;
import com.example.rentacarapp.domain.reservation.ReservationController;
import com.example.rentacarapp.domain.reservation.ReservationDTO;
import com.example.rentacarapp.domain.reservation.command.FilterReservationsCommand;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@ViewScoped
public class ReservationsView {

    private final ReservationController reservationController;
    private final SelectOneMenuService selectOneMenuService;

    public ReservationsView(ReservationController reservationController, SelectOneMenuService selectOneMenuService) {
        this.reservationController = reservationController;
        this.selectOneMenuService = selectOneMenuService;
    }

    private final FilterReservationsCommand filterReservationsCommand = new FilterReservationsCommand();
    private List<ReservationDTO> reservations;
    private ScheduleModel eventModel;
    private String vehicleColorsCSS = "";
    private Map<String, Integer> selectOneMenuVehicleTypes = new LinkedHashMap<>();
    private Map<String, Long> selectOneMenuVehicles = new LinkedHashMap<>();
    private Map<String, Long> selectOneMenuCustomers = new LinkedHashMap<>();


    @PostConstruct
    public void init() {
        populateSelectMenusWithSelectItems();
        updateReservations();
    }

    public void updateReservations() {
        loadReservations();
        createReservationEvents();
    }

    private void populateSelectMenusWithSelectItems() {
        selectOneMenuVehicleTypes = selectOneMenuService.getSelectOneMenuVehicleTypes();
        selectOneMenuVehicles = selectOneMenuService.getSelectOneMenuVehicles();
        selectOneMenuCustomers = selectOneMenuService.getSelectOneMenuCustomers();
    }

    private void loadReservations() {
        reservations = reservationController.findFilteredReservations(filterReservationsCommand);
    }

    private void createReservationEvents() {
        eventModel = new DefaultScheduleModel();

        reservations.forEach(r -> {

            String vehicleColor = r.getVehicle().getColorCode();
            vehicleColorsCSS = vehicleColorsCSS.concat(buildVehicleColorCSS(vehicleColor));

            DefaultScheduleEvent<Object> event = DefaultScheduleEvent.builder()
                    .title(r.getVehicle().getFullNameWithLicensePlates())
                    .startDate(r.getPickupTime())
                    .endDate(r.getReturnTime())
                    .styleClass(generateCustomColorClassName(vehicleColor))
                    .url("/reservation/reservation.xhtml?reservationId=" + r.getId())
                    .build();
            eventModel.addEvent(event);
        });
    }

    public String buildVehicleColorCSS(String color) {
        String className = generateCustomColorClassName(color);

        return "body .fc .fc-event." + className + ", " +
                "body .fc .fc-event." + className + "  .fc-event-main  {\n" +
                "    background: #" + color + " !important;\n" +
                "    border: 0px solid #" + color + " !important;\n" +
                "    color: #ffffff !important;\n" +
                "}";
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public String getVehicleColorsCSS() {
        return vehicleColorsCSS;
    }

    private String generateCustomColorClassName(String color) {
        return "custom-color-class-" + color;
    }

    public List<ReservationDTO> getReservations() {
        return reservations;
    }

    public Map<String, Integer> getSelectOneMenuVehicleTypes() {
        return selectOneMenuVehicleTypes;
    }

    public Map<String, Long> getSelectOneMenuVehicles() {
        return selectOneMenuVehicles;
    }

    public Map<String, Long> getSelectOneMenuCustomers() {
        return selectOneMenuCustomers;
    }

    public FilterReservationsCommand getFilterReservationsCommand() {
        return filterReservationsCommand;
    }


}
