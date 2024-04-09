package TQS.Homework.Layers.Controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import TQS.Homework.Entities.Reservation;
import TQS.Homework.Repo.BusConnectionRepo;
import TQS.Homework.Services.IMPL.ReservationServiceIMPL;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationServiceIMPL reservationService;

    @Test
    public void testCreateReservation() throws Exception {
        when(reservationService.createReservation(anyString(), anyString(), anyInt(), anyInt(), anyLong()))
            .thenReturn("Success");

        mockMvc.perform(post("/api/reservations")
            .param("name", "John")
            .param("email", "john@example.com")
            .param("phone", "123456789")
            .param("seat", "1")
            .param("busConnectionId", "1"))
            .andExpect(status().isOk());
    }



    @Test
    public void testGetAvailableSeats() throws Exception {
        List<Integer> availableSeats = Arrays.asList(1, 2, 3, 4, 5);
        when(reservationService.getAvailableSeats(anyLong()))
            .thenReturn(availableSeats);

        mockMvc.perform(get("/api/reservations/seats")
            .param("busConnectionId", "1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(5)));
    }
    
    @Test
    public void testGetAvailableSeatsEmpty() throws Exception {
        when(reservationService.getAvailableSeats(anyLong()))
            .thenReturn(null);

        mockMvc.perform(get("/api/reservations/seats")
            .param("busConnectionId", "1"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetLastReservation() throws Exception {
        Reservation lastReservation = new Reservation("John", "john@example.com", 123456789, 3 ,1L);
        when(reservationService.getLastReservation())
            .thenReturn(lastReservation);

        mockMvc.perform(get("/api/reservations/last"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("John"))
            .andExpect(jsonPath("$.email").value("john@example.com"))
            .andExpect(jsonPath("$.phone").value(123456789))
            .andExpect(jsonPath("$.busConnectionId").value(1));
    }

    @Test
    public void testGetLastReservationEmpty() throws Exception {
        when(reservationService.getLastReservation())
            .thenReturn(null);

        mockMvc.perform(get("/api/reservations/last"))
            .andExpect(status().isNotFound());
    }
}
