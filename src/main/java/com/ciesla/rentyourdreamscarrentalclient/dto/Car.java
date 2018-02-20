package com.ciesla.rentyourdreamscarrentalclient.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class Car {

    private Integer id;

    @NotNull
    private String brandName;

    @NotNull
    private String model;

    @NotNull
    private Integer year;

    @NotNull
    private String type;

    @NotNull
    private Double engineCapacity;

    @NotNull
    private Integer horsePower;

    @NotNull
    private Integer numberOfCylinders;

    @NotNull
    private Double priceForOneDay;

    @NotNull
    private String description;

    private Integer timesRented;
    private Integer rentedToUserId;
    private boolean availability;
    private List<Account> userRequests;

    public Car() {
    }

    public Car(Integer id, String brandName, String model, Integer year, Double engineCapacity, Integer horsePower, Integer numberOfCylinders, Double priceForOneDay, boolean availability) {
        this.id = id;
        this.brandName = brandName;
        this.model = model;
        this.year = year;
        this.engineCapacity = engineCapacity;
        this.horsePower = horsePower;
        this.numberOfCylinders = numberOfCylinders;
        this.priceForOneDay = priceForOneDay;
        this.availability = availability;
    }
}
