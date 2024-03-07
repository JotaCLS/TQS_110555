


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import static org.slf4j.LoggerFactory.getLogger;
import static java.lang.invoke.MethodHandles.lookup;



@ExtendWith(SeleniumJupiter.class)
class B_Test {

    static final Logger log = getLogger(lookup().lookupClass());
    
    @Test
    void test(EdgeDriver driver) {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl); 
        String title = driver.getTitle();  
        log.debug("The title of {} is {}", sutUrl, title);

        // Verify
        Assertions.assertEquals("Hands-On Selenium WebDriver with Java", title);
    }


}