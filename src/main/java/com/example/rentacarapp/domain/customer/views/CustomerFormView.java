package com.example.rentacarapp.domain.customer.views;

import com.example.rentacarapp.domain.customer.CustomerController;
import com.example.rentacarapp.domain.customer.CustomerDTO;
import com.example.rentacarapp.domain.customer.command.CustomerCommand;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class CustomerFormView implements Serializable {

    private final CustomerController customerController;

    public CustomerFormView(CustomerController customerController) {
        this.customerController = customerController;
    }

    private FacesContext facesContext = FacesContext.getCurrentInstance();

    private CustomerDTO existingCustomer = new CustomerDTO();
    private CustomerCommand customerCommand = new CustomerCommand();


    @PostConstruct
    public void init() {
        if (getCustomerIdFromParams() != null) {
            initializeCustomerData();
        }
    }

    public String addCustomer() {
        customerController.addCustomer(customerCommand);
        return "customers?faces-redirect=true";
    }

    public String editCustomer() {
        customerController.editCustomer(existingCustomer.getId(), customerCommand);
        return "customers?faces-redirect=true";
    }

    public String deleteCustomer(Long customerId) {
        customerController.deleteCustomer(customerId);
        return "customers?faces-redirect=true";
    }


    private void initializeCustomerData() {
        Long customerId = Long.parseLong(getCustomerIdFromParams());
        existingCustomer = customerController.findCustomerById(customerId);
        customerCommand = mapDTOtoCommand(existingCustomer);
    }

    private String getCustomerIdFromParams() {
        return facesContext.getExternalContext()
                .getRequestParameterMap()
                .get("customerId");
    }

    public CustomerDTO getExistingCustomer() {
        return existingCustomer;
    }

    public void setExistingCustomer(CustomerDTO customerDTO) {
        this.existingCustomer = customerDTO;
    }

    public CustomerCommand getCustomerCommand() {
        return customerCommand;
    }

    public void setCustomerCommand(CustomerCommand customerCommand) {
        this.customerCommand = customerCommand;
    }

    /**
     * Maps CustomerDTO to CustomerCommand.
     */
    private CustomerCommand mapDTOtoCommand(CustomerDTO customerDTO) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerDTO, CustomerCommand.class);
    }

}
