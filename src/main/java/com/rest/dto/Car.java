package com.rest.dto;

import com.rest.util.LocalDateTimeXMLAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;

public class Car {

    private String licencePlate;
    private String color;

//    @XmlJavaTypeAdapter(LocalDateTimeXMLAdapter.class)    // Alternative 1. Alternative 2 is through package-info.java!!
    private LocalDateTime enteredDate;

    private String overnight;

    public Car() {
    }

    public Car(String licencePlate, String color, LocalDateTime enteredDate, String overnight) {
        this.licencePlate = licencePlate;
        this.color = color;
        this.enteredDate = enteredDate;
        this.overnight = overnight;
    }

    public Car(String licencePlate, String color, LocalDateTime enteredDate) {
        this.licencePlate = licencePlate;
        this.color = color;
        this.enteredDate = enteredDate;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDateTime getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(LocalDateTime enteredDate) {
        this.enteredDate = enteredDate;
    }

    public String getOvernight() {
        return overnight;
    }

    public void setOvernight(String overnight) {
        this.overnight = overnight;
    }
}
