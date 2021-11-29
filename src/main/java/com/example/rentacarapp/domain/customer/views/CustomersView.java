package com.example.rentacarapp.domain.customer.views;

import com.example.rentacarapp.domain.customer.CustomerController;
import com.example.rentacarapp.domain.customer.CustomerDTO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class CustomersView {

    private final CustomerController customerController;

    private List<CustomerDTO> customers;

    public CustomersView(CustomerController customerController) {
        this.customerController = customerController;
    }

    @PostConstruct
    public void init() {
        customers = customerController.findAllCustomers();
    }

    public List<CustomerDTO> getCustomers() { return customers; }

}
