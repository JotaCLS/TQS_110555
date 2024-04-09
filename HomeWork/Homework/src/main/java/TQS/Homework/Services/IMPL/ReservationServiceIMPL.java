package TQS.Homework.Services.IMPL;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import TQS.Homework.Entities.BusConnection;
import TQS.Homework.Entities.Reservation;
import TQS.Homework.Repo.BusConnectionRepo;
import TQS.Homework.Repo.ReservationRepo;
import TQS.Homework.Services.ReservationService;

@Service
public class ReservationServiceIMPL implements ReservationService{
    
    private final ReservationRepo reservationRepo;
    private final BusConnectionRepo busConnectionRepo;

    @Autowired
    public ReservationServiceIMPL(ReservationRepo reservationRepo, BusConnectionRepo busConnectionRepo) {
        this.reservationRepo = reservationRepo;
        this.busConnectionRepo = busConnectionRepo;
    }

    public String createReservation(String name, String email, Integer phone, Integer seat, Long busConnectionId) {
        try {
            Reservation reservation = new Reservation(name, email, phone, seat, busConnectionId);
            reservationRepo.save(reservation);

            BusConnection busConnection = busConnectionRepo.findById(busConnectionId).orElse(null);
            if (busConnection == null) {
                return "Invalid bus connection ID";
            }

            List<Integer> seats = busConnection.getSeats();
            if (seat < 1 || seat > seats.size()) {
                return "Invalid seat number";
            }

            // Verifica se o assento já está reservado
            if (seats.get(seat - 1) == 0) {
                return "Seat already reserved";
            }

            // Marca o assento como reservado (0 indica que está reservado)
            seats.set(seat - 1, 0);
            busConnection.setSeats(seats);
            busConnectionRepo.save(busConnection); // Salva as mudanças no banco de dados

            return "Reservation created successfully";
        } catch (DataIntegrityViolationException e) {
            // Tratar a exceção quando ocorre uma violação de restrição de chave única
            return "Duplicate reservation data. Reservation not created.";
        }
    }

    public List<Integer> getAvailableSeats(Long busConnectionId) {
        BusConnection busConnection = busConnectionRepo.findById(busConnectionId).orElse(null);
        if (busConnection == null) {
            return Collections.emptyList();
        }

        return busConnection.getSeats();
    }

    public Reservation getLastReservation() {
        List<Reservation> reservations = reservationRepo.findAll();
        if (reservations.isEmpty()) {
            return null;
        }

        return reservations.get(reservations.size() - 1);
    }
}
