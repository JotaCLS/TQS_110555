package TQS.Homework.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import TQS.Homework.Entities.Reservation;

@Service
public interface ReservationService {
    
    public String createReservation(String name, String email, Integer phone, Integer seat, Long busConnectionId);
    public List<Integer> getAvailableSeats(Long busConnectionId);
    public Reservation getLastReservation();
}
