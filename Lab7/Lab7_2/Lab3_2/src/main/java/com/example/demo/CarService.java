package com.example.demo;

import java.util.List;

public interface CarService {

    public Car save(Car car);

    public Car getCarByName(String name);
    public Car getCarById(Long id);
    public boolean exists(String name);
    public List<Car> getAllCars();
} 
