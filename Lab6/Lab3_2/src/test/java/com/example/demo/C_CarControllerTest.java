package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.Car;
import com.example.demo.CarService;
import com.example.demo.CarsController;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarsController.class)
public class C_CarControllerTest {
    
    @Autowired
    private MockMvc mvc;    //entry point to the web framework

    @MockBean
    private CarService service;


    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenPostCar_thenCreateCar( ) throws Exception {
        Car Honda = new Car("honda", "Civic");

        when( service.save(Mockito.any()) ).thenReturn(Honda);

        byte[] content = JsonUtils.toJson(Honda);

        mvc.perform(
                post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(Honda)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("honda")))
                .andExpect(jsonPath("$.model", is("Civic")));

        verify(service, times(1)).save(Mockito.any());

    }

}
