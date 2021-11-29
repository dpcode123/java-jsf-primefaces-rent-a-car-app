package com.example.rentacarapp.domain.vehicle;

import com.example.rentacarapp.domain.vehicle.command.VehicleCommand;
import com.example.rentacarapp.domain.vehicle.make.VehicleMakeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMakeRepository vehicleMakeRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMakeRepository vehicleMakeRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMakeRepository = vehicleMakeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleDTO> findAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleDTO findVehicleById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .map(this::mapEntityToDTO)
                .orElseThrow(() -> new RuntimeException("Vehicle not found."));
    }

    @Override
    @Transactional
    public VehicleDTO addVehicle(VehicleCommand vehicleCommand) {
        Vehicle vehicle = mapCommandToEntity(vehicleCommand);

        return Optional.of(vehicleRepository.save(vehicle))
                .map(this::mapEntityToDTO)
                .orElseThrow(() -> new RuntimeException("Vehicle not added."));
    }

    @Override
    @Transactional
    public VehicleDTO editVehicle(Long vehicleId, VehicleCommand vehicleCommand) {
        if(!vehicleRepository.existsById(vehicleId)) {
            throw new RuntimeException("Vehicle not found.");
        }

        Vehicle vehicle = mapCommandToEntity(vehicleCommand);
        vehicle.setId(vehicleId);

        return Optional.of(vehicleRepository.save(vehicle))
                .map(this::mapEntityToDTO)
                .orElseThrow(() -> new RuntimeException("Vehicle not edited."));
    }

    @Override
    @Transactional
    public Boolean deleteVehicle(Long vehicleId) {
        Long deletedVehiclesCount = vehicleRepository.removeById(vehicleId);
        if (deletedVehiclesCount.equals(0L)) {
            throw new RuntimeException("Vehicle not deleted.");
        }
        return true;
    }

    @Override
    public List<VehicleDTO> findVehiclesAvailableByDateRange(LocalDateTime pickupTime, LocalDateTime returnTime) {
        return vehicleRepository.findVehiclesAvailableByDateRange(pickupTime, returnTime)
                .stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Maps VehicleCommand to Vehicle.
     */
    private Vehicle mapCommandToEntity(final VehicleCommand vehicleCommand) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleMake(vehicleMakeRepository.getById(vehicleCommand.getVehicleMakeId()));
        vehicle.setModelName(vehicleCommand.getModelName());
        vehicle.setLicensePlates(vehicleCommand.getLicensePlates());
        vehicle.setVehicleType(VehicleType.getValue(Math.toIntExact(vehicleCommand.getVehicleTypeId())));
        vehicle.setPricePerDay(vehicleCommand.getPricePerDay());
        vehicle.setMinimumDriversAge(vehicleCommand.getMinimumDriversAge());
        vehicle.setColorCode(vehicleCommand.getColorCode());
        return vehicle;
    }

    /**
     * Maps Vehicle to VehicleDTO.
     */
    private VehicleDTO mapEntityToDTO(final Vehicle vehicle) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(vehicle, VehicleDTO.class);
    }
}
