package TQS.Homework.Layers.Services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import TQS.Homework.Entities.Reservation;
import TQS.Homework.Repo.BusConnectionRepo;
import TQS.Homework.Repo.ReservationRepo;
import TQS.Homework.Services.IMPL.ReservationServiceIMPL;
import TQS.Homework.Entities.BusConnection;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock(lenient = true)
    private ReservationRepo reservationRepo;

    @Mock(lenient = true)
    private BusConnectionRepo busConnectionRepo;

    @InjectMocks
    private ReservationServiceIMPL reservationService;

    @BeforeEach
    void setUp() {
        Reservation reservation = new Reservation("Joao", "joao@hotmail.com", 123456789, 1, 1L);
        Reservation reservation2 = new Reservation("Joao", "joao@hotmail.com", 123456789, 4, 1L);
        reservation2.setToken("123456");
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        BusConnection busConnection = new BusConnection("Porto", "Lisboa", new Date(2021, 10, 10, 10, 0), new Date(2021, 10, 10, 15, 0), 20.0f, seats);

        Mockito.when(reservationRepo.save(reservation)).thenReturn(reservation);
        
        Mockito.when(busConnectionRepo.findById(1L)).thenReturn(Optional.of(busConnection));
        Mockito.when(busConnectionRepo.findById(-3L)).thenReturn(Optional.empty());
        Mockito.when(reservationRepo.findAll()).thenReturn(Arrays.asList(reservation, reservation2));
    }

    // Write tests here

    @Test
    void whenCreateReservation_thenReturnSuccess() {

        // when
        String result = reservationService.createReservation("Joao", "joao@hotmail.com", 123456789, 1, 1L);

        // then

        assertThat(result).isEqualTo("Reservation created successfully");
    }

    @Test
    void whenCreateReservation_thenReturnInvalidBusConnectionID() {

        // when
        String result = reservationService.createReservation("Joao", "joao@hotmail.com", 123456789, 1, -3L);

        // then
        assertThat(result).isEqualTo("Invalid bus connection ID");
    }

    @Test
    void whenCreateReservation_thenReturnInvalidSeatNumber() {

        // when
        String result = reservationService.createReservation("Joao", "joao@hotmail.com", 123456789, -4, 1L);

        // then
        assertThat(result).isEqualTo("Invalid seat number");
    }

    @Test
    void whenCreateReservation_thenReturnSeatAlreadyReserved() {
        // when
        String result = reservationService.createReservation("Joao", "joao@hotmail.com", 123456789, 4, 1L);
        String result2 = reservationService.createReservation("Maria", "maria@hotmail.com", 987654321, 4, 1L);

        // then
        assertThat(result).isEqualTo("Reservation created successfully");
        assertThat(result2).isEqualTo("Seat already reserved");
    }

    @Test
    void whenCreateReservationDiffBus_thenReturnOk() {

        // when
        String result = reservationService.createReservation("Joao", "joao@hotmail.com", 123456789, 4, 1L);
        String result2 = reservationService.createReservation("Maria", "maria@hotmail.com", 987654321, 4, 1L);

        // then
        assertThat(result).isEqualTo("Reservation created successfully");
        assertThat(result2).isEqualTo("Seat already reserved");
    }

    @Test
    void whenCreateReservation_thenReturnSeatAlreadyReserverd2() {
    
        // when
        String result = reservationService.createReservation("Joao", "joao@hotmail.com", 123456789, 4, 1L);
        String result2 = reservationService.createReservation("Joao", "joao@hotmail.com", 123456789, 4, 1L);

        // then
        assertThat(result).isEqualTo("Reservation created successfully");
        assertThat(result2).isEqualTo("Seat already reserved");

    }

    @Test
    void whenGetAvailableSeats_thenReturnSeats() {
        // given
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        // when
        List<Integer> result = reservationService.getAvailableSeats(1L);

        // then
        assertThat(result).isEqualTo(seats);
    }

    @Test
    void whenGetAvailableSeats_thenReturnNull() {
        // when
        List<Integer> result = reservationService.getAvailableSeats(-3L);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void whenGetLastReservation_thenReturnReservation() {
        // given
        Reservation reservation2 = new Reservation("Joao", "joao@hotmail.com", 123456789, 1, 1L);
        Reservation reservation = new Reservation("Joao", "joao@hotmail.com", 123456789, 4, 1L);

        reservation.setToken("123456");

        // when
        Reservation result = reservationService.getLastReservation();

        // then
        assertThat(result.getName()).isEqualTo(reservation.getName());
        assertThat(result.getEmail()).isEqualTo(reservation.getEmail());
        assertThat(result.getPhone()).isEqualTo(reservation.getPhone());
    
    }

}
