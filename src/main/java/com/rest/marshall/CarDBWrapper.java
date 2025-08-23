package com.rest.marshall;

import com.rest.dto.Car;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.HashMap;

@XmlRootElement
public class CarDBWrapper {

    private HashMap<Integer, Car> cars;

    public HashMap<Integer, Car> getCars() {
        return cars;
    }

    public void setCars(HashMap<Integer, Car> cars) {
        this.cars = cars;
    }
}
