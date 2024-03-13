import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Confirmation {
    private final WebDriver driver;

    public Confirmation(WebDriver driver) {
        this.driver = driver;
    }

    public void confirmPurchase() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(By.cssSelector("h1")).getText();
    }
}