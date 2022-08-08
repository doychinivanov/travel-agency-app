package com.travelapp.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BookingCreateDTO {

    @NotBlank(message = "Phone number is required!")
    @Size(max = 13, message = "Enter a valid phone number.")
    @Pattern(regexp = "^[+0-9]+$", message = "Please, enter a valid phone number.")
    private String phoneNumber;

    private String comment;

    public BookingCreateDTO() {}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "BookingCreateDTO{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
