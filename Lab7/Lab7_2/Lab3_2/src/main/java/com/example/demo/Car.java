package com.example.demo;

import jakarta.persistence.*;




@Entity
@Table(name = "tqs_cars")
public class Car {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String model;

    public Car(String name, String model) {
        this.name = name;
        this.model = model;
    }

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }


    
}
