package com.example.rentacarapp.domain.vehicle.views;

import com.example.rentacarapp.domain.selectonemenu.SelectOneMenuService;
import com.example.rentacarapp.domain.vehicle.VehicleController;
import com.example.rentacarapp.domain.vehicle.VehicleDTO;
import com.example.rentacarapp.domain.vehicle.command.VehicleCommand;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@ViewScoped
public class VehicleFormView implements Serializable {

    private final VehicleController vehicleController;
    private final SelectOneMenuService selectOneMenuService;

    public VehicleFormView(VehicleController vehicleController, SelectOneMenuService selectOneMenuService) {
        this.vehicleController = vehicleController;
        this.selectOneMenuService = selectOneMenuService;
    }

    private VehicleDTO existingVehicle;
    private VehicleCommand vehicleCommand = new VehicleCommand();
    private Map<String, Integer> selectOneMenuVehicleTypes = new LinkedHashMap<>();
    private Map<String, Long> selectOneMenuVehicleMakes = new LinkedHashMap<>();

    private FacesContext facesContext = FacesContext.getCurrentInstance();

    @PostConstruct
    public void init() {
        populateSelectMenusWithSelectItems();

        if (getVehicleIdFromParams() != null) {
            initializeVehicleData();
        }
    }

    private void populateSelectMenusWithSelectItems() {
        selectOneMenuVehicleTypes = selectOneMenuService.getSelectOneMenuVehicleTypes();
        selectOneMenuVehicleMakes = selectOneMenuService.getSelectOneMenuVehicleMakes();
    }

    private void initializeVehicleData() {
        Long vehicleId = Long.parseLong(getVehicleIdFromParams());
        existingVehicle = vehicleController.findVehicleById(vehicleId);
        vehicleCommand = mapVehicleDTOtoCommand(existingVehicle);
    }

    public String addVehicle() {
        vehicleController.addVehicle(vehicleCommand);
        return "vehicles?faces-redirect=true";
    }

    public String editVehicle() {
        vehicleController.editVehicle(existingVehicle.getId(), vehicleCommand);
        return "vehicles?faces-redirect=true";
    }

    public String deleteVehicle(Long vehicleId) {
        vehicleController.deleteVehicle(vehicleId);
        return "vehicles?faces-redirect=true";
    }


    private VehicleCommand mapVehicleDTOtoCommand(VehicleDTO vehicleDTO) {
        VehicleCommand command = new VehicleCommand();
        command.setVehicleTypeId(vehicleDTO.getVehicleType().ordinal());
        command.setVehicleMakeId(vehicleDTO.getVehicleMake().getId());
        command.setLicensePlates(vehicleDTO.getLicensePlates());
        command.setModelName(vehicleDTO.getModelName());
        command.setMinimumDriversAge(vehicleDTO.getMinimumDriversAge());
        command.setPricePerDay(vehicleDTO.getPricePerDay());
        command.setColorCode(vehicleDTO.getColorCode());
        return command;
    }

    private String getVehicleIdFromParams() {
        return facesContext.getExternalContext()
                .getRequestParameterMap()
                .get("vehicleId");
    }


    public Map<String, Integer> getSelectOneMenuVehicleTypes() {
        return selectOneMenuVehicleTypes;
    }

    public void setSelectOneMenuVehicleTypes(Map<String, Integer> selectOneMenuVehicleTypes) {
        this.selectOneMenuVehicleTypes = selectOneMenuVehicleTypes;
    }

    public Map<String, Long> getSelectOneMenuVehicleMakes() {
        return selectOneMenuVehicleMakes;
    }

    public void setSelectOneMenuVehicleMakes(Map<String, Long> selectOneMenuVehicleMakes) {
        this.selectOneMenuVehicleMakes = selectOneMenuVehicleMakes;
    }

    public VehicleDTO getExistingVehicle() {
        return existingVehicle;
    }

    public void setExistingVehicle(VehicleDTO existingVehicle) {
        this.existingVehicle = existingVehicle;
    }

    public VehicleCommand getVehicleCommand() {
        return vehicleCommand;
    }

    public void setVehicleCommand(VehicleCommand vehicleCommand) {
        this.vehicleCommand = vehicleCommand;
    }


}
