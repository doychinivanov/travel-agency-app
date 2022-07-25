package com.travelapp.models.dto;

import com.travelapp.models.Country;

import java.math.BigDecimal;

public class TripDetailsDTO {

    private long id;

    private String destination;

    private Country country;

    private String highlight;

    private BigDecimal price;

    private int days;

    private String img;

    private String description;

    public TripDetailsDTO() {}

    @Override
    public String toString() {
        return "TripDetailsDTO{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", country=" + country +
                ", highlight='" + highlight + '\'' +
                ", price=" + price +
                ", days=" + days +
                ", img='" + img + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
