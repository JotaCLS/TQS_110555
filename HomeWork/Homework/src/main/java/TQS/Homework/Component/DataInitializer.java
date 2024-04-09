package TQS.Homework.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

import TQS.Homework.Entities.BusConnection;
import TQS.Homework.Repo.BusConnectionRepo;


@Component
public class DataInitializer implements CommandLineRunner {

    private final BusConnectionRepo busConnectionRepo;


    public DataInitializer(BusConnectionRepo busConnectionRepo) {
        this.busConnectionRepo = busConnectionRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // Clear the database
        busConnectionRepo.deleteAll();

        // Initialize some sample seats
        List<Integer> seats = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        List<Integer> seats2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> seats3 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);



        // Initialize some sample bus connections
        @SuppressWarnings("deprecation")
        List<BusConnection> busConnections = Arrays.asList(
            new BusConnection("Porto", "Lisboa", new Date(124, 10, 10, 10, 0), new Date(124, 10, 10, 15, 0), 20.0f, seats),
            new BusConnection("Lisboa", "Porto", new Date(124, 10, 10, 10, 0), new Date(124, 10, 10, 15, 0), 20.0f, seats2),
            new BusConnection("Porto", "Lisboa", new Date(124, 10, 10, 10, 0), new Date(124, 10, 10, 15, 0), 25.0f, seats3),
            new BusConnection("Lisboa", "Porto", new Date(124, 10, 10, 10, 0), new Date(124, 10, 10, 15, 0), 21.0f, seats2),
            new BusConnection("Lisboa", "Faro", new Date(124, 10, 10, 10, 0), new Date(124, 10, 10, 15, 0), 15.0f, seats3),
            new BusConnection("Faro", "Lisboa", new Date(124, 10, 10, 10, 0), new Date(124, 10, 10, 15, 0), 15.0f, seats3)
        );

        // Save the bus connections to the database
        busConnectionRepo.saveAll(busConnections);
    }
}
