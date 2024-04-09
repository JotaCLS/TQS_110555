package TQS.Homework.Layers.Services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import TQS.Homework.Entities.BusConnection;
import TQS.Homework.Repo.BusConnectionRepo;
import TQS.Homework.Services.IMPL.BusConnectionServiceIMPL;

@ExtendWith(MockitoExtension.class)
public class BusConnectionsServiceTest {
    
    @Mock(lenient = true)
    private BusConnectionRepo busConnectionsRepo;

    @InjectMocks
    private BusConnectionServiceIMPL busConnectionsService;

    @BeforeEach
    void setUp() {
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        BusConnection busConnection1 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        BusConnection busConnection2 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 8, 0), new Date(2021, 10, 10, 13, 0), 20.0f, seats);
        BusConnection busConnection3 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 12, 0), new Date(2021, 10, 10, 17, 0), 20.0f, seats);
        BusConnection busConnection4 = new BusConnection("Lisboa", "Porto", new Date(2021, 10, 10, 14, 0), new Date(2021, 10, 10, 19, 0), 20.0f, seats);


        List<BusConnection> busConnections1 = Arrays.asList(busConnection1);
        List<BusConnection> busConnections2 = Arrays.asList(busConnection1, busConnection2, busConnection3);

        Mockito.when(busConnectionsRepo.findByOriginAndDestinationAndDepartureDate(busConnection1.getOrigin(), busConnection1.getDestination(), busConnection1.getDepartureDate())).thenReturn(busConnections1);
        Mockito.when(busConnectionsRepo.findByOriginAndDestination(busConnection1.getOrigin(), busConnection1.getDestination())).thenReturn(busConnections2);
        Mockito.when(busConnectionsRepo.findByOrigin(busConnection1.getOrigin())).thenReturn(busConnections2);
        Mockito.when(busConnectionsRepo.findByDestination(busConnection1.getDestination())).thenReturn(busConnections2);
        Mockito.when(busConnectionsRepo.findAll()).thenReturn(Arrays.asList(busConnection1, busConnection2, busConnection3, busConnection4));
        Mockito.when(busConnectionsRepo.findById(busConnection1.getId())).thenReturn(java.util.Optional.of(busConnection1));
        when(busConnectionsRepo.save(any(BusConnection.class))).thenReturn(busConnection1);
        Mockito.when(busConnectionsRepo.findByOriginAndDestinationAndDepartureDate("Joao", "Santos", new Date(2021, 10, 10, 10, 0))).thenReturn(Arrays.asList());
        Mockito.when(busConnectionsRepo.findByOriginAndDestination("Joao", "Santos")).thenReturn(Arrays.asList());
        Mockito.when(busConnectionsRepo.findByOrigin("Joao")).thenReturn(Arrays.asList());  
        Mockito.when(busConnectionsRepo.findByDestination("Santos")).thenReturn(Arrays.asList());

    }

    // Write tests here
    @Test
    void getBusConnections_WhenOriginDestinationAndDepartureDate_thenReturnBusConnections() {
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        BusConnection busConnection1 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        List<BusConnection> found = busConnectionsService.getBusConnections("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0));

        
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getOrigin()).isEqualTo(busConnection1.getOrigin());
        assertThat(found.get(0).getDestination()).isEqualTo(busConnection1.getDestination());
        assertThat(found.get(0).getDepartureDate()).isEqualTo(busConnection1.getDepartureDate());
        assertThat(found.get(0).getArrivalDate()).isEqualTo(busConnection1.getArrivalDate());
        
    }

    void whenSearchInvalidOriginDestinationAndDepartureDate_thenReturnEmptyList() {
        List<BusConnection> found = busConnectionsService.getBusConnections("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0));

        assertThat(found).isEmpty();
    }

    @Test
    void getBusConnections_WhenOriginAndDestination_thenReturnBusConnections() {
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        BusConnection busConnection1 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        BusConnection busConnection2 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 8, 0), new Date(2021, 10, 10, 13, 0), 20.0f, seats);
        BusConnection busConnection3 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 12, 0), new Date(2021, 10, 10, 17, 0), 20.0f, seats);
        List<BusConnection> found = busConnectionsService.getBusConnections("Porto", "Lisboa", null);

        assertThat(found).hasSize(3);
        assertThat(found.get(0).getOrigin()).isEqualTo(busConnection1.getOrigin());
        assertThat(found.get(1).getOrigin()).isEqualTo(busConnection2.getOrigin());
        assertThat(found.get(2).getOrigin()).isEqualTo(busConnection3.getOrigin());
        assertThat(found.get(0).getDestination()).isEqualTo(busConnection1.getDestination());
        assertThat(found.get(1).getDestination()).isEqualTo(busConnection2.getDestination());
        assertThat(found.get(2).getDestination()).isEqualTo(busConnection3.getDestination());
    }

    @Test
    void whenSearchInvalidOriginAndDestination_thenReturnEmptyList() {
        List<BusConnection> found = busConnectionsService.getBusConnections("Joao", "Santos", null);

        assertThat(found).isEmpty();
    }

    @Test
    void getBusConnections_WhenOrigin_thenReturnBusConnections() {
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        BusConnection busConnection1 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        BusConnection busConnection2 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 8, 0), new Date(2021, 10, 10, 13, 0), 20.0f, seats);
        BusConnection busConnection3 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 12, 0), new Date(2021, 10, 10, 17, 0), 20.0f, seats);
        List<BusConnection> found = busConnectionsService.getBusConnections("Porto", "", null);


        assertThat(found).hasSize(3);
        assertThat(found.get(0).getOrigin()).isEqualTo(busConnection1.getOrigin());
        assertThat(found.get(1).getOrigin()).isEqualTo(busConnection2.getOrigin());
        assertThat(found.get(2).getOrigin()).isEqualTo(busConnection3.getOrigin());
    }

    @Test
    void whenSearchInvalidOrigin_thenReturnEmptyList() {
        List<BusConnection> found = busConnectionsService.getBusConnections("Joao", "", null);

        assertThat(found).isEmpty();
    }

    @Test
    void getBusConnections_WhenDestination_thenReturnBusConnections() {
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        BusConnection busConnection1 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        BusConnection busConnection2 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 8, 0), new Date(2021, 10, 10, 13, 0), 20.0f, seats);
        BusConnection busConnection3 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 12, 0), new Date(2021, 10, 10, 17, 0), 20.0f, seats);
        List<BusConnection> found = busConnectionsService.getBusConnections("", "Lisboa", null);

        assertThat(found).hasSize(3);
        assertThat(found.get(0).getDestination()).isEqualTo(busConnection1.getDestination());
        assertThat(found.get(1).getDestination()).isEqualTo(busConnection2.getDestination());
        assertThat(found.get(2).getDestination()).isEqualTo(busConnection3.getDestination());
    }

    @Test 
    void whenSearchInvalidDestination_thenReturnEmptyList() {
        List<BusConnection> found = busConnectionsService.getBusConnections("", "Santos", null);

        assertThat(found).isEmpty();
    }


    @Test
    void getBusConnectionById_thenReturnBusConnection() {
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        BusConnection busConnection1 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        BusConnection found = busConnectionsService.getBusConnectionById(busConnection1.getId()).get();

        assertThat(found.getOrigin()).isEqualTo(busConnection1.getOrigin());
        assertThat(found.getDestination()).isEqualTo(busConnection1.getDestination());
        assertThat(found.getDepartureDate()).isEqualTo(busConnection1.getDepartureDate());
        assertThat(found.getArrivalDate()).isEqualTo(busConnection1.getArrivalDate());
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        BusConnection found = busConnectionsService.getBusConnectionById(-1L).orElse(null);

        assertThat(found).isNull();
    }

    @Test 
    void getBusConnectionByNothing() {
        List<BusConnection> found = busConnectionsService.getBusConnections("", "", null);

        assertThat(found).hasSize(4);
    }

    @Test
    void addBusConnection_thenReturnBusConnection() {
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        BusConnection busConnection1 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        BusConnection found = busConnectionsService.addBusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, 20);

        System.out.println("Bus connection:" + busConnection1);
        System.out.println(found);

        assertThat(found.getOrigin()).isEqualTo(busConnection1.getOrigin());
        assertThat(found.getDestination()).isEqualTo(busConnection1.getDestination());
        assertThat(found.getDepartureDate()).isEqualTo(busConnection1.getDepartureDate());
        assertThat(found.getArrivalDate()).isEqualTo(busConnection1.getArrivalDate());
    }



}
