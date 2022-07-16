package com.travelapp.models.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class CreateTripDTO {

    @NotBlank(message = "This field is required!")
    private String destination;

    @NotBlank(message = "This field is required!")
    private String highlight;
    @Positive(message = "Price must be positive number!")
    @NotNull(message = "This field is required!")
    private BigDecimal price;

    @Min(value = 1, message = "The minimum days is one!")
    @Max(value = 30, message = "The maximum days are 30!")
    private int days;

    @NotBlank(message = "This field is required!")
    private String description;

    @Override
    public String toString() {
        return "CreateTripDTO{" +
                "destination='" + destination + '\'' +
                ", highlight='" + highlight + '\'' +
                ", price=" + price +
                ", days=" + days +
                ", description='" + description + '\'' +
                '}';
    }

    public CreateTripDTO() {}

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
