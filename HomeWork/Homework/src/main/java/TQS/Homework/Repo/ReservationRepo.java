package TQS.Homework.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TQS.Homework.Entities.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long>{
    
    Reservation findByToken(String token);
    
}
