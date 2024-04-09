package TQS.Homework.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TQS.Homework.Entities.BusConnection;

@Service
public interface BusConnectionService {
    
    public List<BusConnection> getBusConnections(String origin, String destination, Date departureDate);
    public Optional<BusConnection> getBusConnectionById(Long id);
    public BusConnection addBusConnection(String origin, String destination, Date departureDate, Date arrivalDate, float price, int seats);
}
