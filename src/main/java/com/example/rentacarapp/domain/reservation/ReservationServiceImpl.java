package com.example.rentacarapp.domain.reservation;

import com.example.rentacarapp.domain.customer.CustomerRepository;
import com.example.rentacarapp.domain.reservation.command.FilterReservationsCommand;
import com.example.rentacarapp.domain.reservation.command.ReservationCommand;
import com.example.rentacarapp.domain.reservation.command.SearchReservationsByDateCommand;
import com.example.rentacarapp.domain.vehicle.VehicleRepository;
import com.example.rentacarapp.domain.vehicle.VehicleType;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, CustomerRepository customerRepository, VehicleRepository vehicleRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<ReservationDTO> findAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationDTO findReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .map(this::mapEntityToDTO)
                .orElseThrow(() -> new RuntimeException("Reservation not found."));
    }

    @Override
    @Transactional
    public ReservationDTO addReservation(ReservationCommand reservationCommand) {
        Reservation reservation = mapCommandToEntity(reservationCommand);

        return Optional.of(reservationRepository.save(reservation))
                .map(this::mapEntityToDTO)
                .orElseThrow(() -> new RuntimeException("Reservation not added."));
    }

    @Override
    @Transactional
    public ReservationDTO editReservation(Long reservationId, ReservationCommand reservationCommand) {
        if (!reservationRepository.existsById(reservationId)) {
            throw new RuntimeException("Reservation not found.");
        }

        Reservation reservation = mapCommandToEntity(reservationCommand);
        reservation.setId(reservationId);

        return Optional.of(reservationRepository.save(reservation))
                .map(this::mapEntityToDTO)
                .orElseThrow(() -> new RuntimeException("Reservation not edited."));
    }

    @Override
    @Transactional
    public Boolean deleteReservation(Long reservationId) {
        Long deletedReservationsCount = reservationRepository.removeById(reservationId);
        if (deletedReservationsCount.equals(0L)) {
            throw new RuntimeException("Reservation not deleted.");
        }
        return true;
    }


    @Override
    @Transactional(readOnly = true)
    public List<ReservationDTO> findReservationsByVehicle(Long vehicleId) {
        return reservationRepository.findReservationsByVehicleId(vehicleId)
                .stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationDTO> findReservationsByVehicleType(Integer vehicleTypeId) {
        return reservationRepository.findReservationsByVehicleVehicleType(VehicleType.getValue(vehicleTypeId))
                .stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public List<ReservationDTO> filterReservations(FilterReservationsCommand command) {
        Reservation probe = new Reservation();

        if (command.getVehicleId() != null) {
            probe.setVehicle(vehicleRepository.getById(command.getVehicleId()));
        }
        if (command.getCustomerId() != null) {
            probe.setCustomer(customerRepository.getById(command.getCustomerId()));
        }

        List<Reservation> reservations = reservationRepository.findAll(Example.of(probe));

        if (command.getVehicleTypeId() != null) {
            reservations = reservations.stream()
                    .filter(r -> r.getVehicle()
                            .getVehicleType()
                            .equals(VehicleType.getValue(command.getVehicleTypeId())))
                    .collect(Collectors.toList());
        }

        return reservations.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> searchReservationsByDate(SearchReservationsByDateCommand searchReservationsByDateCommand) {
        List<Reservation> reservations;

        String pickupOrReturn = searchReservationsByDateCommand.getPickupOrReturn();
        LocalDateTime timePeriodFrom = searchReservationsByDateCommand.getTimePeriodFrom();
        LocalDateTime timePeriodTo = searchReservationsByDateCommand.getTimePeriodTo();

        if (pickupOrReturn.equals("pickup")) {
            reservations = reservationRepository
                    .findReservationByPickupTimeAfterAndPickupTimeBeforeOrderByPickupTime(
                            timePeriodFrom, timePeriodTo);
        } else {
            reservations = reservationRepository
                    .findReservationByReturnTimeAfterAndReturnTimeBeforeOrderByReturnTime(
                            timePeriodFrom, timePeriodTo);
        }

        return reservations.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }


    /**
     * Maps ReservationCommand to Reservation.
     */
    private Reservation mapCommandToEntity(final ReservationCommand reservationCommand) {
        Reservation reservation = new Reservation();
        reservation.setCustomer(customerRepository.getById(reservationCommand.getCustomerId()));
        reservation.setVehicle(vehicleRepository.getById(reservationCommand.getVehicleId()));
        reservation.setPickupTime(reservationCommand.getPickupTime());
        reservation.setReturnTime(reservationCommand.getReturnTime());
        reservation.setTotalPrice(reservation.calculateTotalPrice());
        return reservation;
    }

    /**
     * Maps Reservation to ReservationDTO.
     */
    private ReservationDTO mapEntityToDTO(final Reservation reservation) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reservation, ReservationDTO.class);
    }


}
