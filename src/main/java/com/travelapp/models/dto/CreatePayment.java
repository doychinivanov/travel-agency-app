package com.travelapp.models.dto;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class CreatePayment {
    @SerializedName("items")
    Object[] items;

    @SerializedName("price")
    BigDecimal price;

    public Object[] getItems() {
        return items;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
