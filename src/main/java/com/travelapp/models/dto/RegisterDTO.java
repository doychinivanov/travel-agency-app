package com.travelapp.models.dto;

import com.travelapp.utils.validations.EqualPasswordsConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualPasswordsConstraint(message = "Passwords don't match")
public class RegisterDto {

    @NotBlank(message = "This field is required!")
    @Size(min = 2, max = 50, message = "Your name must be between 2 and 50 characters long!")
    private String fullName;

    @NotBlank(message = "This field is required!")
    @Email(message = "Please, enter a valid email.")
    private String email;

    @NotBlank(message = "This field is required!")
    @Size(min = 3, message = "Password must be at least 6 symbols.")
    private String password;

    private String confirmPass;

    public RegisterDto() {}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }
}
