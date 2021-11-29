package com.example.rentacarapp.domain.customer.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CustomerCommand {

    @NotBlank(message = "First name is empty.")
    private String firstName;

    @NotBlank(message = "First name is empty.")
    private String lastName;

    @Email(message = "Email is not valid.")
    private String email;

    public CustomerCommand(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public CustomerCommand() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
