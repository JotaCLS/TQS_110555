import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.seljup.BrowserType;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumJupiter.class)
public class DockerChromeTest {
    private Homepage homepage;
    private Reservation reservation;
    private Confirmation confirmation;

    @BeforeEach
    public void setUp(@DockerBrowser(type = BrowserType.CHROME) RemoteWebDriver driver) {
        driver.manage().window().maximize();
        homepage = new Homepage(driver);
        reservation = new Reservation(driver);
        confirmation = new Confirmation(driver);
    }

    @Test
    public void testWithChrome() {
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
