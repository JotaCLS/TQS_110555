package TQS.Homework.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import TQS.Homework.Entities.Reservation;

import TQS.Homework.Services.IMPL.ReservationServiceIMPL;


@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    
    private final ReservationServiceIMPL reservationService;

    @Autowired
    public ReservationController(ReservationServiceIMPL reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestParam String name, @RequestParam String email,
                                                    @RequestParam Integer phone, @RequestParam Integer seat,
                                                    @RequestParam Long busConnectionId) {
        String result = reservationService.createReservation(name, email, phone, seat, busConnectionId);
        
        return ResponseEntity.ok(result);
    }

    @GetMapping("/seats")
    public ResponseEntity<List<Integer>> getAvailableSeats(@RequestParam Long busConnectionId) {
        List<Integer> seats = reservationService.getAvailableSeats(busConnectionId);
        if (seats == null) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(seats);
    }

    @GetMapping("/last")
    public ResponseEntity<Reservation> getLastReservation() {
        Reservation reservation = reservationService.getLastReservation();
        if (reservation == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reservation);
    }
}
