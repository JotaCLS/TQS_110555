package TQS.Homework.Layers.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import TQS.Homework.Controller.BusConnectionController;
import TQS.Homework.DTO.BusConnectionDTO;
import TQS.Homework.Entities.BusConnection;
import TQS.Homework.Repo.BusConnectionRepo;
import TQS.Homework.Services.CurrencyAPI;

@SpringBootTest
@AutoConfigureMockMvc
public class BusConnectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusConnectionRepo busConnectionRepo;

    @MockBean
    private CurrencyAPI currencyAPI;

    @Test
    public void testGetBusConnections() throws Exception {
        // Configurar o mock do repositório para retornar dados específicos
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        when(busConnectionRepo.findByOriginAndDestinationAndDepartureDate(anyString(), anyString(), any(Date.class)))
            .thenReturn(Arrays.asList(new BusConnection("Lisbon", "Porto", new Date(2024, 4, 8), new Date(2024, 4, 8), 100.0f, seats)));

        // Enviar uma solicitação GET para o controlador
        mockMvc.perform(get("/api/busConnections")
            .param("origin", "Lisbon")
            .param("destination", "Porto")
            .param("departureDate", "2024-04-08")
            .param("currency", "USD"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test 
    public void testGetBusConnectionsEmptyParams() throws Exception {
        // Configurar o mock do repositório para retornar dados específicos
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        when(busConnectionRepo.findAll())
            .thenReturn(Arrays.asList(new BusConnection("Lisbon", "Porto", new Date(2024, 4, 8), new Date(2024, 4, 8), 100.0f, seats)));

        // Enviar uma solicitação GET para o controlador
        mockMvc.perform(get("/api/busConnections")
            .param("origin", "")
            .param("destination", "")
            .param("departureDate", "")
            .param("currency", ""))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetBusConnectionsCurrency() throws Exception {
        // Configurar o mock do repositório para retornar dados específicos
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        when(busConnectionRepo.findByOriginAndDestinationAndDepartureDate(anyString(), anyString(), any(Date.class)))
            .thenReturn(Arrays.asList(new BusConnection("Lisbon", "Porto", new Date(2024, 4, 8), new Date(2024, 4, 8), 100.0f, seats)));
        when(currencyAPI.getExchangeRate("EUR"))
            .thenReturn(1.0f);

        // Enviar uma solicitação GET para o controlador
        mockMvc.perform(get("/api/busConnections")
            .param("origin", "Lisbon")
            .param("destination", "Porto")
            .param("departureDate", "2024-04-08")
            .param("currency", "EUR"))
            .andExpect(status().isOk());
    }

    @Test 
    public void testGetBusConnectionWrongId() throws Exception {
        // Configurar o mock do repositório para retornar um dado específico
        when(busConnectionRepo.findById(-1L))
            .thenReturn(java.util.Optional.empty());

        // Enviar uma solicitação GET para o controlador
        mockMvc.perform(get("/api/busConnections/-1"))
            .andExpect(status().isNotFound());
    }

    @Test 
    public void testGetBusConnection() throws Exception {
        // Configurar o mock do repositório para retornar um dado específico
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        when(busConnectionRepo.findById(1L))
            .thenReturn(java.util.Optional.of(new BusConnection("Lisbon", "Porto", new Date(2024, 4, 8), new Date(2024, 4, 8), 100.0f, seats)));

        // Enviar uma solicitação GET para o controlador
        mockMvc.perform(get("/api/busConnections/1"))
            .andExpect(status().isOk());
    }

    @Test
    public void testAddBusConnection() throws Exception {
        // Configurar o mock do repositório para não fazer nada (ou fazer algo específico se necessário)
        when(busConnectionRepo.save(any(BusConnection.class)))
            .thenReturn(new BusConnection());

        // Enviar uma solicitação POST para o controlador
        mockMvc.perform(post("/api/busConnections")
            .param("origin", "Lisbon")
            .param("destination", "Porto")
            .param("departureDate", "2024-04-08")
            .param("arrivalDate", "2024-04-08")
            .param("price", "100.0")
            .param("seats", "50"))
            .andExpect(status().isOk());
    }

    @Test
    void testConvertToDTO() {
        // Configurar os objetos BusConnection simulados
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        List<BusConnection> busConnections = Arrays.asList(new BusConnection("Lisbon", "Porto", new Date(2024, 4, 8), new Date(2024, 4, 8), 100.0f, seats));
        BusConnectionController controller = new BusConnectionController(busConnectionRepo, currencyAPI);

        // Chamar o método convertToDTO
        List<BusConnectionDTO> dtos = controller.convertToDTO(busConnections);

        // Verificar se os objetos BusConnectionDTO gerados têm os valores esperados
        assertEquals(busConnections.size(), dtos.size());
        for (int i = 0; i < busConnections.size(); i++) {
            BusConnection connection = busConnections.get(i);
            BusConnectionDTO dto = dtos.get(i);

            assertEquals(connection.getId(), dto.getId());
            assertEquals(connection.getOrigin(), dto.getOrigin());
            assertEquals(connection.getDestination(), dto.getDestination());
            assertEquals(connection.getDepartureDate(), dto.getDepartureDate());
            assertEquals(connection.getArrivalDate(), dto.getArrivalDate());
            assertEquals(connection.getPrice(), dto.getPrice());
            assertEquals(connection.getSeats().size(), dto.getSeats());
        }
    }
}


