package TQS.Homework.Layers.Controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import TQS.Homework.Entities.Reservation;
import TQS.Homework.Repo.ReservationRepo;

@SpringBootTest
@AutoConfigureMockMvc
public class CheckReservationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationRepo reservationRepo;

    // Write tests here

    @Test
    public void testCheckReservation() throws Exception {
        when(reservationRepo.findByToken(anyString()))
            .thenReturn(new Reservation("Joao", "joao@hotmail.com", 123456789, 1, 1L));
        
        mockMvc.perform(get("/api/checkReservation")
            .param("reservationToken", "123456789"))
            .andExpect(status().isOk());
    }

    @Test
    public void testCheckReservationNotFound() throws Exception {
        when(reservationRepo.findByToken(anyString()))
            .thenReturn(null);
        
        mockMvc.perform(get("/api/checkReservation")
            .param("reservationToken", "123456789"))
            .andExpect(status().isNotFound());
    }
}
