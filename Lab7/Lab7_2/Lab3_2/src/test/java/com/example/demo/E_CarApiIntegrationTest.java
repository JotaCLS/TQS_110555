package com.example.demo;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import com.example.demo.Car;
import com.example.demo.CarRepository;
import org.junit.jupiter.api.Disabled;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase


class E_CarApiIntegrationTest {

    @LocalServerPort
    int randomServerPort;

    
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }


    @Disabled
    @Test
     void whenValidInput_thenCreateCar() {
        Car Honda = new Car("honda", "civic");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", Honda, Car.class);

        List<Car> found = repository.findAll();
        System.out.println("found: " + found);
        assertThat(found).extracting(Car::getName).containsOnly("honda");
    }

    @Test
     void givenCars_whenGetCars_thenStatus200()  {
        createTestCar("honda", "civic");
        createTestCar("ferrari", "f40");


        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getName).containsExactly("honda", "ferrari");

    }


    private void createTestCar(String name, String model) {
        Car emp = new Car(name, model);
        repository.saveAndFlush(emp);
    }

}
