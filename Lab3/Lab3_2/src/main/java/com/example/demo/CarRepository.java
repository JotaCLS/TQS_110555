package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
    
    public Car findByName(String name);
    public List<Car> findAll();
    public Car findByModel(String model);

}   

