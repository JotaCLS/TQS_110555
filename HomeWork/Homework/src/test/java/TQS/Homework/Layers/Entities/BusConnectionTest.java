package TQS.Homework.Layers.Entities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TQS.Homework.Entities.BusConnection;
import TQS.Homework.DTO.BusConnectionDTO;

public class BusConnectionTest {

    BusConnection busConnection = new BusConnection();

    @BeforeEach 
    void setUp() {
        busConnection = new BusConnection();
    }

    // Testting the getters and setters
    @Test
    public void testGettersAndSetters() {
        List <Integer> seats = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            seats.add(i);
        }

        busConnection.setOrigin("origin");
        busConnection.setDestination("destination");
        busConnection.setDepartureDate(new Date());
        busConnection.setArrivalDate(new Date());
        busConnection.setPrice(10);
        busConnection.setSeats(seats);

        assertThat(busConnection.getOrigin()).isEqualTo("origin");
        assertThat(busConnection.getDestination()).isEqualTo("destination");
        assertThat(busConnection.getDepartureDate()).isNotNull();
        assertThat(busConnection.getArrivalDate()).isNotNull();
        assertThat(busConnection.getPrice()).isEqualTo(10);
        assertThat(busConnection.getSeats()).isEqualTo(seats);


    }

    // Testing the constructor
    @Test
    public void testConstructor() {
        List <Integer> seats = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            seats.add(i);
        }

        BusConnection busConnection = new BusConnection("origin", "destination", new Date(), new Date(), 10, seats);

        assertThat(busConnection.getOrigin()).isEqualTo("origin");
        assertThat(busConnection.getDestination()).isEqualTo("destination");
        assertThat(busConnection.getDepartureDate()).isNotNull();
        assertThat(busConnection.getArrivalDate()).isNotNull();
        assertThat(busConnection.getPrice()).isEqualTo(10);
        assertThat(busConnection.getSeats()).isEqualTo(seats);

    }

    // Testing the id
    @Test
    public void testId() {
        busConnection.setId(10L);
        assertThat(busConnection.getId()).isEqualTo(10L);
    }

    // Testing the invalid price
    @Test
    public void testInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> busConnection.setPrice(-10));
    }

    // Testing the invalid seats
    // if the number of seats is greater than 50, throws an exception
    public void testInvalidSeats() {
        List <Integer> seats = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            seats.add(i);
        }
        assertThrows(IllegalArgumentException.class, () -> new BusConnection().setSeats(seats));
    }
    
    // Testing the invalid seats
    // If the number of seats is less than 0, the number of seats is set to 0
    @Test
    public void testInvalidSeats2() {
        List <Integer> seats = new ArrayList<>();
        for (int i = 0; i < -10; i++) {
            seats.add(i);
        }
        assertThrows(IllegalArgumentException.class, () -> new BusConnection().setSeats(seats));

    }

    @Test
    void constructorDTO() {
        BusConnectionDTO busConnectionDTO = new BusConnectionDTO("origin", "destination", new Date(), new Date(), 10, 30);

        assertThat(busConnectionDTO.getOrigin()).isEqualTo("origin");
        assertThat(busConnectionDTO.getDestination()).isEqualTo("destination");
        assertThat(busConnectionDTO.getDepartureDate()).isNotNull();
        assertThat(busConnectionDTO.getArrivalDate()).isNotNull();
        assertThat(busConnectionDTO.getPrice()).isEqualTo(10);
        assertThat(busConnectionDTO.getSeats()).isEqualTo(30);
    }

}