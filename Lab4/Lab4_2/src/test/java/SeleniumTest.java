// Generated by Selenium IDE
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.edge.EdgeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;


public class SeleniumTest {

  private WebDriver driver;

  private Map<String, Object> vars;

  JavascriptExecutor js;

  @BeforeEach
  public void setUp() {
    driver = new EdgeDriver();
  }
  

  @Test
  public void teste() {

    String sutUrl = "https://blazedemo.com/";
    driver.get(sutUrl);
    driver.manage().window().setSize(new Dimension(1280, 832));
    driver.findElement(By.name("fromPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Boston']")).click();
    }
    driver.findElement(By.name("toPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'Rome']")).click();
    }
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Joao");
    driver.findElement(By.id("address")).sendKeys("Santos");
    driver.findElement(By.id("city")).sendKeys("Mozelos");
    driver.findElement(By.id("state")).sendKeys("Mozelos");
    driver.findElement(By.id("zipCode")).sendKeys("1234");
    driver.findElement(By.id("creditCardNumber")).sendKeys("13124124");
    driver.findElement(By.id("nameOnCard")).sendKeys("1321231");
    driver.findElement(By.cssSelector(".btn-primary")).click();
    String title = driver.getTitle();
    Assertions.assertEquals("BlazeDemo Confirmation", title);
  }

  @Test
  public void Selenium1Test() {
    System.out.println("In test 1");
		driver.get("http://google.com");
		String expectedPageTitle = "Google";
		String actualPageTitle = driver.getTitle();
    Assertions.assertEquals(expectedPageTitle, actualPageTitle);

  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }
}
