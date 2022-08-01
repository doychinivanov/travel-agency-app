package com.travelapp.models.dto;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class CreatePayment {
    @SerializedName("items")
    Object[] items;

    @SerializedName("price")
    BigDecimal price;

    @SerializedName("email")
    private String email;

    @SerializedName("bookingId")
    private long bookingId;

    @SerializedName("featureRequest")
    private String featureRequest;

    @SerializedName("phone")
    private String phone;

    public Object[] getItems() {
        return items;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getEmail() {
        return email;
    }

    public long getBookingId() {
        return bookingId;
    }

    public String getFeatureRequest() {
        return featureRequest;
    }

    public String getPhone() {
        return phone;
    }
}
