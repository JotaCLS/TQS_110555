package TQS.Homework.Layers.Repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import TQS.Homework.Entities.Reservation;
import TQS.Homework.Repo.ReservationRepo;

@DataJpaTest
public class ReservationRepoTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReservationRepo reservationRepo;

    // Write tests here
    @Test
    void whenFindById_thenReturnReservation() {
        // given 
        Reservation reservation = new Reservation("Joao", "joao@hotmal.coim", 123456789, 1, 1L);
        entityManager.persistAndFlush(reservation);
        
        // when
        Reservation found = reservationRepo.findById(reservation.getId()).get();

        // then
        assertThat(found.getId()).isEqualTo(reservation.getId());
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        // given
        Reservation reservation = new Reservation("Joao", "joao@hotmal.coim", 123456789, 1, 1L);
        entityManager.persistAndFlush(reservation);

        // when
        assertThat(reservationRepo.findById(-1L)).isEmpty();
    }

    @Test
    void whenFindByToken_thenReturnReservation() {
        // given
        Reservation reservation = new Reservation("Joao", "joao@hotmal.coim", 123456789, 1, 1L);
        entityManager.persistAndFlush(reservation);

        // when
        Reservation found = reservationRepo.findByToken(reservation.getToken());

        // then
        assertThat(found.getToken()).isEqualTo(reservation.getToken());
    }

}
