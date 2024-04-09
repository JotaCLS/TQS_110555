package TQS.Homework.Services.IMPL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TQS.Homework.Entities.BusConnection;
import TQS.Homework.Repo.BusConnectionRepo;

@Service
public class BusConnectionServiceIMPL {

    private final BusConnectionRepo busConnectionRepo;

    @Autowired
    public BusConnectionServiceIMPL(BusConnectionRepo busConnectionRepo) {
        this.busConnectionRepo = busConnectionRepo;
    }


    public List<BusConnection> getBusConnections(String origin, String destination, Date departureDate) {
        if (!origin.equals("") && !destination.equals("") && departureDate != null) {
            return busConnectionRepo.findByOriginAndDestinationAndDepartureDate(origin, destination, departureDate);
        } else if (!origin.equals("") && !destination.equals("")) {
            return busConnectionRepo.findByOriginAndDestination(origin, destination);
        } else if (!origin.equals("")) {
            return busConnectionRepo.findByOrigin(origin);
        } else if (!destination.equals("")) {
            return busConnectionRepo.findByDestination(destination);
        } else {
            return busConnectionRepo.findAll();
        }
    }

    public Optional<BusConnection> getBusConnectionById(Long id) {
        return busConnectionRepo.findById(id);
    }

    public BusConnection addBusConnection(String origin, String destination, Date departureDate, Date arrivalDate, float price, int seats) {
        List<Integer> seatList = new ArrayList<>();
        for (int i = 0; i < seats; i++) {
            seatList.add(i + 1);
        }
        BusConnection connection = new BusConnection(origin, destination, departureDate, arrivalDate, price, seatList);
        busConnectionRepo.save(connection);
        return connection;
    }
}

