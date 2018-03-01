package com.ciesla.rentyourdreamscarrentalclient.service;

import com.ciesla.rentyourdreamscarrentalclient.dto.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class CarService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rental.service.url}")
    private String rentalServiceUrl;

    public List<Car> findAll() {
        return restTemplate.getForObject(rentalServiceUrl + "/car/show/all", List.class);
    }

    public List<Car> findAllByPopularity() {
        return restTemplate.getForObject(rentalServiceUrl + "/car/show/all/popular", List.class);
    }

    public Car findCarById(Integer id) {
        return restTemplate.getForObject(rentalServiceUrl + "/car/show/id/" + id, Car.class);
    }

    public void addNewCar(Car car) {
        car.setAvailability(true);
        car.setTimesRented(0);
        restTemplate.postForObject(rentalServiceUrl + "/car/add", car, String.class);
    }

    public void updateCar(Car car) {
        restTemplate.put(rentalServiceUrl + "/car/update", car);
    }

    public void deleteCar(Integer id) {
        restTemplate.delete(rentalServiceUrl + "/car/delete/" + id);
    }

    public List<Car> findCarsByKeyword(String keyword) {
        String correctedKeyword = keyword.substring(0, 1).toUpperCase() + keyword.substring(1);

        List<Car> carsFound = restTemplate.getForObject(rentalServiceUrl + "/car/show/model/" + correctedKeyword, List.class);
        List<Car> matchedByBrandName = restTemplate.getForObject(rentalServiceUrl + "/car/show/brand/" + correctedKeyword, List.class);
        carsFound.addAll(matchedByBrandName);
        return carsFound;
    }

    public Integer getNumberOfRequests() {
        return restTemplate.getForObject(rentalServiceUrl + "/car/requests", Integer.class);
    }
}
