package TQS.Homework.Repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TQS.Homework.Entities.BusConnection;

@Repository
public interface BusConnectionRepo extends JpaRepository<BusConnection, Long>{

    public List<BusConnection> findByOriginAndDestination(String origin, String destination);
    public List<BusConnection> findByOrigin(String origin);
    public List<BusConnection> findByDestination(String destination);
    public List<BusConnection> findByOriginAndDestinationAndDepartureDate(String origin, String destination, Date departureDate);

    
}
