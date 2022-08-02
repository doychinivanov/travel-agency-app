package com.travelapp.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Table(name = "trips")
@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String destination;

    @ManyToOne
    private Country country;

    @Column(nullable = false)
    private String highlight;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int days;

    @Column(name = "image", nullable = false)
    private String img;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany
    private Set<Booking> bookings;

    public Trip() {}

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> booking) {
        this.bookings = booking;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
