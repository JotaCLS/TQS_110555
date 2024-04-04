package com.example.demo;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Car;
import com.example.demo.CarDTO;
import com.example.demo.CarService;


@RestController
@RequestMapping("/api")
public class CarsController {


    private final CarService carService;   


    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/cars" )
    public ResponseEntity<Car> createCar(@RequestBody CarDTO car) {
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carService.save( car.toCarEntity() );
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping(path="/cars" )
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }



}
