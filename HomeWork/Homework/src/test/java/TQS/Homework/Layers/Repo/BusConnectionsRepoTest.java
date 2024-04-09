package TQS.Homework.Layers.Repo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import TQS.Homework.Entities.BusConnection;
import TQS.Homework.Repo.BusConnectionRepo;

@DataJpaTest
public class BusConnectionsRepoTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BusConnectionRepo busConnectionsRepo;

    // Write tests here
    @Test
    void whenFindById_thenReturnBusConnection() {
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        // given
        BusConnection busConnection = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        entityManager.persistAndFlush(busConnection);

        // when
        BusConnection found = busConnectionsRepo.findById(busConnection.getId()).get();

        // then
        assertThat(found.getId()).isEqualTo(busConnection.getId());
    }

    @Test 
    void whenInvalidId_thenReturnNull() {
        // given
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        BusConnection busConnection = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        entityManager.persistAndFlush(busConnection);

        // when
        Optional<BusConnection> found = busConnectionsRepo.findById(-1L);

        // then
        assertThat(found).isEmpty();
    }

    @Test
    void whenFindByOrigin_thenReturnBusConnections() {
        // given
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        BusConnection busConnection1 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        entityManager.persistAndFlush(busConnection1);
        
        BusConnection busConnection2 = new BusConnection("Porto", "Faro", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        entityManager.persistAndFlush(busConnection2);
        // when
        List<BusConnection> found = busConnectionsRepo.findByOrigin("Porto");


        // then
        assertThat(found).contains(busConnection1, busConnection2);
    }

    @Test
    void whenFindByDestination_thenReturnBusConnections() {
        // given
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        BusConnection busConnection1 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        entityManager.persistAndFlush(busConnection1);
        
        BusConnection busConnection2 = new BusConnection("Faro", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        entityManager.persistAndFlush(busConnection2);
        // when
        List<BusConnection> found = busConnectionsRepo.findByDestination("Lisboa");

        // then

        assertThat(found).contains(busConnection1, busConnection2);
    }

    @Test
    void whenFindByOriginAndDestination_thenReturnBusConnections() {
        // given
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        BusConnection busConnection1 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        entityManager.persistAndFlush(busConnection1);
        
        BusConnection busConnection2 = new BusConnection("Faro", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        entityManager.persistAndFlush(busConnection2);
        // when
        List<BusConnection> found = busConnectionsRepo.findByOriginAndDestination("Porto", "Lisboa");

        // then
        assertThat(found).contains(busConnection1);
    }

    @Test
    void whenFindByOriginAndDestinationAndDepartureDate_thenReturnBusConnections() {
        // given
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        BusConnection busConnection1 = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);
        entityManager.persistAndFlush(busConnection1);
        
        BusConnection busConnection2 = new BusConnection("Faro", "Lisboa", new Date(2021, 10, 11, 10, 0), new Date(2021, 10, 11, 15, 0), 20.0f, seats);
        entityManager.persistAndFlush(busConnection2);
        // when
        List<BusConnection> found = busConnectionsRepo.findByOriginAndDestinationAndDepartureDate("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0));

        // then
        assertThat(found).contains(busConnection1);
    }

}
