


import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;
import static java.lang.invoke.MethodHandles.lookup;




class A_Test {

    static final Logger log = getLogger(lookup().lookupClass());

    private WebDriver driver; 

    @BeforeEach
    void setup() {
        driver = new EdgeDriver(); 
    }

    @Test
    void test() {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl); 
        String title = driver.getTitle(); 
        log.debug("The title of {} is {}", sutUrl, title); 

        // Verify
        Assertions.assertEquals("Hands-On Selenium WebDriver with Java", title);
    }

    @AfterEach
    void teardown() {
        driver.quit(); 
    }

}