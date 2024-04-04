package com.example.demo;



public class CarDTO {
    private String name;
    private String model;

    public CarDTO(String name, String model) {
        this.name = name;
        this.model = model;
    }

    public CarDTO() {
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }
    
    public Car toCarEntity() {
        return new Car(name, model);
    }
    
}
