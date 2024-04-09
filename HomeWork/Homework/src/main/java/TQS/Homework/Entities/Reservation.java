package TQS.Homework.Entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {

    private static final int MAX_SEATS = 50;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "token")
    private String token;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private int phone;

    @Column(name = "bus_connection_id")
    private Long busConnectionId;

    @Column(name = "seat")
    private int seat;

    
    public Reservation() {
        this.token=UUID.randomUUID().toString(); 
    }

    public Reservation(String name, String email, int phone, int seat, Long busConnectionId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.seat = seat;
        this.busConnectionId = busConnectionId;
        this.token=UUID.randomUUID().toString(); 
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public int getSeat() {
        return seat;
    }

    public Long getBusConnectionId() {
        return busConnectionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }

    public void setPhone(int phone) {
        if (!isValidPhone(phone)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        this.phone = phone;
    }

    public void setBusConnectionId(Long busConnectionId) {
        this.busConnectionId = busConnectionId;
    }

    public void setSeat(int seat) {
        if (!isValidSeat(seat)) {
            throw new IllegalArgumentException("Invalid seat number");
        }
        this.seat = seat;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "Reservation [busConnectionId=" + busConnectionId + ", email=" + email + ", id=" + id + ", name=" + name
                + ", phone=" + phone + ", seat=" + seat + ", token=" + token + "]";
    }

    private boolean isValidPhone(int phone) {
        return String.valueOf(phone).length() == 9; // Supondo que o número de telefone tenha 10 dígitos
    }

    private boolean isValidSeat(int seat) {
        return seat > 0 && seat <= MAX_SEATS; // Supondo que MAX_SEATS é o número máximo de assentos disponíveis
    }

    private boolean isValidEmail(String email) {
        return email.contains("@");
    }

}