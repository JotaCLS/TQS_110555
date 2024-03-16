package test.java;

import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BlazeTest {

    private final WebDriver driver = new EdgeDriver();

    @Given("I am on the Blaze Demo site")
    public void i_am_on_the_blazedemo_homepage() {
        driver.get("https://blazedemo.com/");
    }

    @When("I select the departure city as {string}")
    public void i_select_as_departure_city(String string) {
        WebElement departureCity = driver.findElement(By.name("fromPort"));
        departureCity.sendKeys(string);
    }

    @When("I select the destination city as {string}")
    public void i_select_as_destination_city(String string) {
        WebElement destinationCity = driver.findElement(By.name("toPort"));
        destinationCity.sendKeys(string);
    }

    @When("I click on the Find Flights button")
    public void i_click_on_the_find_flights_button() {
        WebElement findFlightsButton = driver.findElement(By.xpath("//input[@value='Find Flights']"));
        findFlightsButton.click();
    }

    @When("I select the first flight")
    public void i_select_the_first_flight() {
        WebElement firstFlight = driver.findElement(By.xpath("//input[@value='Choose This Flight']"));
        firstFlight.click();
    }

    @When("I fill in the form with the following information")
    public void i_fill_in_the_form_with_the_following_information(io.cucumber.datatable.DataTable dataTable) {
        WebElement name = driver.findElement(By.id("inputName"));
        WebElement address = driver.findElement(By.id("address"));
        WebElement city = driver.findElement(By.id("city"));
        WebElement state = driver.findElement(By.id("state"));
        WebElement zipCode = driver.findElement(By.id("zipCode"));
        WebElement cardType = driver.findElement(By.id("cardType"));
        WebElement creditCardNumber = driver.findElement(By.id("creditCardNumber"));
        WebElement creditCardMonth = driver.findElement(By.id("creditCardMonth"));
        WebElement creditCardYear = driver.findElement(By.id("creditCardYear"));
        WebElement nameOnCard = driver.findElement(By.id("nameOnCard"));

        name.sendKeys(dataTable.cell(1, 0));
        address.sendKeys(dataTable.cell(1, 1));
        city.sendKeys(dataTable.cell(1, 2));
        state.sendKeys(dataTable.cell(1, 3));
        zipCode.sendKeys(dataTable.cell(1, 4));
        cardType.sendKeys(dataTable.cell(1, 5));
        creditCardNumber.sendKeys(dataTable.cell(1, 6));
        creditCardMonth.sendKeys(dataTable.cell(1, 7));
        creditCardYear.sendKeys(dataTable.cell(1, 8));
        nameOnCard.sendKeys(dataTable.cell(1, 9));
    }

    @When("I click on the Purchase Flight button")
    public void i_click_on_the_purchase_flight_button() {
        WebElement purchaseFlightButton = driver.findElement(By.xpath("//input[@value='Purchase Flight']"));
        purchaseFlightButton.click();
    }

    @Then("I should see the message {string}")
    public void i_should_see_the_message(String string) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(string);
            }
        });
    }


}