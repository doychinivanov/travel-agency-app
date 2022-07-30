package com.travelapp.models.dto;

public class BookingCreateDTO {

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
