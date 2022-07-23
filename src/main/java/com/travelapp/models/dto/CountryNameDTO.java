package com.travelapp.models.dto;

public class CountryNameDTO {

    private long id;

    private String name;

    public CountryNameDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
