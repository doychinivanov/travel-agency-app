package com.travelapp.models.dto;

import java.math.BigDecimal;

public class TripCardDTO {

    private long id;

    private String img;

    private String destination;

    private String highlight;

    private BigDecimal price;

    public TripCardDTO() {}

    @Override
    public String toString() {
        return "TripCardDTO{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", destination='" + destination + '\'' +
                ", highlight='" + highlight + '\'' +
                ", price=" + price +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

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
}
