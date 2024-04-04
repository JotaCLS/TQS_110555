package com.example.demo;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.example.demo.Car;
import com.example.demo.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class A_CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void whenFindHondaByName_thenReturnHondaCar() {
        Car Honda = new Car("Honda", "Civic");
        entityManager.persistAndFlush(Honda); 

        // test the query method of interest
        Car found = carRepository.findByName(Honda.getName());
        assertThat(found).isEqualTo(Honda);
    }

    @Test
    void whenInvalidCarName_thenReturnNull() {
        Car fromDb = carRepository.findByName("Does Not Exist");
        assertThat(fromDb).isNull();
    }

    @Test
    void whenFindEmployedByExistingId_thenReturnCar() {
        Car emp = new Car("test", "test@deti.com");
        entityManager.persistAndFlush(emp);

        Car fromDb = carRepository.findById(emp.getId()).orElse(null);
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getModel()).isEqualTo(emp.getModel());
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findById(-111L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car Honda = new Car("Honda", "Civic");
        Car Ferrari = new Car("Ferrari", "F40");
        Car BMW = new Car("BMW", "e36");

        entityManager.persist(Honda);
        entityManager.persist(Ferrari);
        entityManager.persist(BMW);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getName).containsOnly(Honda.getName(), Ferrari.getName(), BMW.getName());
    }

}