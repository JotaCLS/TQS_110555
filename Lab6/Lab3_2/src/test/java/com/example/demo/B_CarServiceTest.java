package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class B_CarServiceTest {
    

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceIMPL carService;

    @BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Car Honda = new Car("Honda", "Civic");
        Honda.setId(111L);

        Car Ferrari = new Car("Ferrari", "F40");
        Car BMW = new Car("BMW", "e36");

        List<Car> allEmployees = Arrays.asList(Honda, Ferrari, BMW);

        Mockito.when(carRepository.findByName(Honda.getName())).thenReturn(Honda);
        Mockito.when(carRepository.findByName(Ferrari.getName())).thenReturn(Ferrari);
        Mockito.when(carRepository.findByName(BMW.getName())).thenReturn(BMW);
        Mockito.when(carRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(carRepository.findById(Honda.getId())).thenReturn(Optional.of(Honda));
        Mockito.when(carRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(carRepository.findById(-99L)).thenReturn(Optional.empty());

    }

    @Test
    void whenSearchValidName_thenCarShouldBeFound() {
        String name = "Ferrari";
        Car found = carService.getCarByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }

    @Test
     void whenSearchInvalidName_thenCarShouldNotBeFound() {
        Car fromDb = carService.getCarByName("wrong_name");
        assertThat(fromDb).isNull();

        verifyFindByNameIsCalledOnce("wrong_name");
    }

    @Test
     void whenValidName_thenCarShouldExist() {
        boolean doesCarExist = carService.exists("Ferrari");
        assertThat(doesCarExist).isTrue();

        verifyFindByNameIsCalledOnce("Ferrari");
    }

    @Test
     void whenNonExistingName_thenCarShouldNotExist() {
        boolean doesCarExist = carService.exists("some_name");
        assertThat(doesCarExist).isFalse();
        verifyFindByNameIsCalledOnce("some_name");
    }

    @Test
     void whenValidId_thenCarShouldBeFound() {
        Car fromDb = carService.getCarById(111L);
        assertThat(fromDb.getName()).isEqualTo("Honda");

        verifyFindByIdIsCalledOnce();
    }

    @Test
     void whenInValidId_thenCarShouldNotBeFound() {
        Car fromDb = carService.getCarById(-99L);
        verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }

    @Test
     void given3Car_whengetAll_thenReturn3Records() {
        Car Honda = new Car("Honda", "Civic");
        Car Ferrari = new Car("Ferrari", "F40");
        Car BMW = new Car("BMW", "e36");

        List<Car> allCars = carService.getAllCars();
        verifyFindAllCarsIsCalledOnce();
        assertThat(allCars).hasSize(3).extracting(Car::getName).contains(Honda.getName(), Ferrari.getName(), BMW.getName());
    }

    private void verifyFindByNameIsCalledOnce(String name) {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByName(name);
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
    }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
    }
}
