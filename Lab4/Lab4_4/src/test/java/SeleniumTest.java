import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SeleniumJupiter.class)
public class SeleniumTest {
    private WebDriver driver;
    private Homepage homepage;
    private Reservation reservation;
    private Confirmation confirmation;


    public SeleniumTest(ChromeDriver driver) {
        this.driver = driver;
    }

    @BeforeEach
    public void setUp() {
        driver.manage().window().maximize();
        homepage = new Homepage(driver);
        reservation = new Reservation(driver);
        confirmation = new Confirmation(driver);
    }


    @Test
    public void teste() {
        homepage.open();
        homepage.selectDeparture("Mexico City");
        homepage.selectDestination("London");
        homepage.searchFlights();
        assertEquals(reservation.getAirlineName(1), "Virgin America");

        reservation.chooseFlight(2);
        reservation.fillPassengerDetails("joao", "rua santa margarida", "mozelos", "santa maria", "1234", "123214", "aaaaaaa");

        confirmation.confirmPurchase();
        assertEquals(confirmation.getConfirmationMessage(), "Thank you for your purchase today!");
    }
}