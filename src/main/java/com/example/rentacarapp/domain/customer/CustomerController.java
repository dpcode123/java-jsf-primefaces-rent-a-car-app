package com.example.rentacarapp.domain.customer;

import com.example.rentacarapp.domain.customer.command.CustomerCommand;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<CustomerDTO> findAllCustomers() {
        return customerService.findAllCustomers();
    }

    public CustomerDTO findCustomerById(Long customerId) {
        return customerService.findCustomerById(customerId);
    }

    public CustomerDTO addCustomer(CustomerCommand customerCommand) {
        return customerService.addCustomer(customerCommand);
    }

    public CustomerDTO editCustomer(Long customerId, CustomerCommand customerCommand) {
        return customerService.editCustomer(customerId, customerCommand);
    }

    public Boolean deleteCustomer(Long customerId) {
        return customerService.deleteCustomer(customerId);
    }

}
