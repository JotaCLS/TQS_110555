package TQS.Homework.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import TQS.Homework.Entities.Reservation;
import TQS.Homework.Repo.ReservationRepo;

@RestController
@RequestMapping("/api/checkReservation")
public class CheckReservationController {
    
    private final ReservationRepo reservationRepo;

    @Autowired
    public CheckReservationController(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    @GetMapping
    public ResponseEntity<Reservation> checkReservation(@RequestParam String reservationToken) {
        Reservation reservation = reservationRepo.findByToken(reservationToken);
        if (reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
}
