package TQS.Homework.Layers.Entities;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import TQS.Homework.Entities.Reservation;


public class ReservationTest {
    //generate me unit test for the Reservation class

    Reservation reservation;
    
    @BeforeEach
    void setUp() {
        reservation = new Reservation();
    }


    // Testting the getters and setters
    @Test
    public void testGettersAndSetters() {
        reservation.setName("name");
        reservation.setEmail("email@123.pt");
        reservation.setPhone(123456789);
        reservation.setSeat(1);
        reservation.setBusConnectionId(1L);
        reservation.setId(1L);

        assertThat(reservation.getName()).isEqualTo("name");
        assertThat(reservation.getEmail()).isEqualTo("email@123.pt");
        assertThat(reservation.getPhone()).isEqualTo(123456789);
        assertThat(reservation.getSeat()).isEqualTo(1);
        assertThat(reservation.getBusConnectionId()).isEqualTo(1L);
        assertThat(reservation.getId()).isEqualTo(1L);

    }

    // Testting the constructor
    @Test
    public void testConstructor() {
        Reservation reservation = new Reservation("name", "email", 123, 1, 1L);

        assertThat(reservation.getName()).isEqualTo("name");
        assertThat(reservation.getEmail()).isEqualTo("email");
        assertThat(reservation.getPhone()).isEqualTo(123);
        assertThat(reservation.getSeat()).isEqualTo(1);
        assertThat(reservation.getBusConnectionId()).isEqualTo(1L);

    }

    // Testting the token
    @Test
    public void testToken() {
        assertThat(reservation.getToken()).isNotNull();
    }

    @Test
    public void testInvalidSeat() {
        assertThrows(IllegalArgumentException.class, () -> reservation.setSeat(0));
        assertThrows(IllegalArgumentException.class, () -> reservation.setSeat(-1));
    }

    @Test
    public void testInvalidPhone() {
        assertThrows(IllegalArgumentException.class, () -> reservation.setPhone(123));
        assertThrows(IllegalArgumentException.class, () -> reservation.setPhone(1234567890));
    }

    @Test
    public void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> reservation.setEmail("email"));
    }
}
