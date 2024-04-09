package TQS.Homework.Entities;


import java.util.Date;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bus_connections")
public class BusConnection {

    private static final int MAX_SEATS = 50;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "departure_date")
    private Date departureDate;

    @Column(name = "arrival_date")
    private Date arrivalDate;

    @Column(name = "price")
    private float price;

    @Column(name = "seats")
    private List<Integer> seats;
    

    

    public BusConnection() {

    }



    public BusConnection(String origin, String destination, Date departureDate, Date arrivalDate, float price, List<Integer> seats) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.price = price;
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }
    
    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public float getPrice() {
        return price;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setPrice(float price) {
        if (!isValidPrice(price)) {
            throw new IllegalArgumentException("Invalid price");
        }
        this.price = price;
    }

    public void setSeats(List<Integer> seats) {
        if (!isValidSeats(seats)) {
            throw new IllegalArgumentException("Too many seats");
        }
        this.seats = seats;
    }

    public boolean isValidSeats(List<Integer> seats) {
        return seats.size() <= MAX_SEATS && seats.size() > 0;
    }

    public boolean isValidPrice(float price) {
        return price >= 0;
    }


}