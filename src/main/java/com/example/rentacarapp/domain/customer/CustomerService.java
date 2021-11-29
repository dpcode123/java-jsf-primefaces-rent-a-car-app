package com.example.rentacarapp.domain.customer;

import com.example.rentacarapp.domain.customer.command.CustomerCommand;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> findAllCustomers();

    CustomerDTO findCustomerById(Long customerId);

    CustomerDTO addCustomer(CustomerCommand customerCommand);

    CustomerDTO editCustomer(Long customerId, CustomerCommand customerCommand);

    Boolean deleteCustomer(Long customerId);

}
