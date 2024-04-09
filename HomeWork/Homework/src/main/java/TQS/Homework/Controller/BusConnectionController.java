package TQS.Homework.Controller;

import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TQS.Homework.Entities.BusConnection;
import TQS.Homework.Repo.BusConnectionRepo;
import TQS.Homework.Component.StringToDate;
import TQS.Homework.DTO.BusConnectionDTO;
import TQS.Homework.Services.CurrencyAPI;


@RestController

@RequestMapping("/api/busConnections")
public class BusConnectionController {

    
    private final BusConnectionRepo busConnectionRepo;
    private final CurrencyAPI currencyAPI;

    @Autowired
    public BusConnectionController(BusConnectionRepo busConnectionRepo, CurrencyAPI currencyAPI) {
        this.busConnectionRepo = busConnectionRepo;
        this.currencyAPI = currencyAPI;
    }


    @GetMapping
    public List<BusConnectionDTO> getBusConnections(@RequestParam String origin, @RequestParam String destination, @RequestParam String departureDate, @RequestParam String currency) throws ParseException {
        List<BusConnectionDTO> busConnections;


        if (!origin.equals("") && !destination.equals("") && !departureDate.equals("")) {
            Date departureDateObj = StringToDate.parseStringToDate(departureDate);
            busConnections = convertToDTO(busConnectionRepo.findByOriginAndDestinationAndDepartureDate(origin, destination, departureDateObj));
        } else if (!origin.equals("") && !destination.equals("")) {
            busConnections = convertToDTO(busConnectionRepo.findByOriginAndDestination(origin, destination));
        } else if (!origin.equals("")) {
            busConnections = convertToDTO(busConnectionRepo.findByOrigin(origin));
        } else if (!destination.equals("")) {
            busConnections = convertToDTO(busConnectionRepo.findByDestination(destination));
        } else {
            busConnections = convertToDTO(busConnectionRepo.findAll());
        }

        if (!currency.equals("")) {
            if ("USD".equals(currency)) {
                return busConnections;
            }
            float exchangeRate = currencyAPI.getExchangeRate(currency);
            for (BusConnectionDTO connection : busConnections) {
                connection.setPrice(connection.getPrice() * exchangeRate);
            }
        }

        return busConnections;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusConnectionDTO> getBusConnectionById(@PathVariable Long id) {
        Optional<BusConnection> optionalBusConnection = busConnectionRepo.findById(id);
        if (optionalBusConnection.isPresent()) {
            BusConnection connection = optionalBusConnection.get();
            BusConnectionDTO dto = new BusConnectionDTO();
            dto.setId(connection.getId());
            dto.setOrigin(connection.getOrigin());
            dto.setDestination(connection.getDestination());
            dto.setDepartureDate(connection.getDepartureDate());
            dto.setArrivalDate(connection.getArrivalDate());
            dto.setPrice(connection.getPrice());
            dto.setSeats(connection.getSeats().size());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public List<BusConnectionDTO> convertToDTO(List<BusConnection> connections) {
        List<BusConnectionDTO> dtos = new ArrayList<>();
        for (BusConnection connection : connections) {
            BusConnectionDTO dto = new BusConnectionDTO();
            dto.setId(connection.getId());
            dto.setOrigin(connection.getOrigin());
            dto.setDestination(connection.getDestination());
            dto.setDepartureDate(connection.getDepartureDate());
            dto.setArrivalDate(connection.getArrivalDate());
            dto.setPrice(connection.getPrice());
            dto.setSeats(connection.getSeats().size());
            dtos.add(dto);
        }
        return dtos;
    }

    @PostMapping
    public ResponseEntity addBusConnection(@RequestParam String origin, @RequestParam String destination, @RequestParam String departureDate, @RequestParam String arrivalDate, @RequestParam float price, @RequestParam int seats) throws ParseException {
        Date departureDateObj = StringToDate.parseStringToDate(departureDate);
        Date arrivalDateObj = StringToDate.parseStringToDate(arrivalDate);
        List<Integer> seatList = new ArrayList<>();
        
        for (int i = 0; i < seats; i++) {
            seatList.add(i + 1);

        }
        BusConnection connection = new BusConnection(origin, destination, departureDateObj, arrivalDateObj, price, seatList);
        busConnectionRepo.save(connection);
        return ResponseEntity.ok("Bus connection created successfully");
    }
}
