package com.example.demo;


import com.example.demo.CarRepository;
import com.example.demo.CarService;


import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.Car;


@Service
public class CarServiceIMPL implements CarService {

    private CarRepository CarRepository;

    public CarServiceIMPL(CarRepository CarRepository) {
        this.CarRepository = CarRepository;
    }
    
    public Car save(Car car) {
        return car;
    }

    public Car getCarByName(String name) {
        return CarRepository.findByName(name);
    }

    public Car getCarById(Long id) {
        return CarRepository.findById(id).orElse(null);
    }

    public boolean exists(String name) {
        return CarRepository.findByName(name) != null;
    }

    public List<Car> getAllCars() {
        return CarRepository.findAll();
    }
}
