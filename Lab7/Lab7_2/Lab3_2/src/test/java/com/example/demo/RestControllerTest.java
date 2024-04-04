package test.java.com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import com.example.demo.CarService;
import com.example.demo.CarsController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;


@WebMvcTest(CarsController.class)
public class RestControllerTest {
    
    @MockBean
    CarService carService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void test1() { 
        RestAssuredMockMvc.mockMvc(mockMvc);

        given().when().get("/api/cars").then().statusCode(200);
    }  
}
