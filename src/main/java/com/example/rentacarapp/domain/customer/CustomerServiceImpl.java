package com.example.rentacarapp.domain.customer;

import com.example.rentacarapp.domain.customer.command.CustomerCommand;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .map(this::mapEntityToDTO)
                .orElseThrow(() -> new RuntimeException("Customer not found."));
    }

    @Override
    @Transactional
    public CustomerDTO addCustomer(CustomerCommand customerCommand) {
        Customer customer = mapCommandToEntity(customerCommand);

        return Optional.of(customerRepository.save(customer))
                .map(this::mapEntityToDTO)
                .orElseThrow(() -> new RuntimeException("Customer not added."));
    }

    @Override
    @Transactional
    public CustomerDTO editCustomer(Long customerId, CustomerCommand customerCommand) {
        if (!customerRepository.existsById(customerId)) {
            throw new RuntimeException("Customer not found.");
        }

        Customer customer = mapCommandToEntity(customerCommand);
        customer.setId(customerId);

        return Optional.of(customerRepository.save(customer))
                .map(this::mapEntityToDTO)
                .orElseThrow(() -> new RuntimeException("Customer not edited."));
    }

    @Override
    @Transactional
    public Boolean deleteCustomer(Long customerId) {
        Long deletedCustomersCount = customerRepository.removeById(customerId);
        if (deletedCustomersCount.equals(0L)) {
            throw new RuntimeException("Customer not deleted.");
        }
        return true;
    }


    /**
     * Maps CustomerCommand to Customer.
     */
    private Customer mapCommandToEntity(final CustomerCommand customerCommand) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerCommand, Customer.class);
    }

    /**
     * Maps Customer to CustomerDTO.
     */
    private CustomerDTO mapEntityToDTO(final Customer customer) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customer, CustomerDTO.class);
    }


}
