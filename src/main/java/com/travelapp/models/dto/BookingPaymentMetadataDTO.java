package com.travelapp.models.dto;

import java.math.BigDecimal;

public class BookingPaymentMetadataDTO {
    String phoneNumber;

    String comment;

    public BookingPaymentMetadataDTO() {}

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
}
