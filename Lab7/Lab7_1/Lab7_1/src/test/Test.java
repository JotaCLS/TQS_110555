import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;


public class Test {
    

    @Test
    void test1() {
        String url = "https://jsonplaceholder.typicode.com/todos";
        
        given().when().get(url).then().statusCode(200);

        String url2 = "https://jsonplaceholder.typicode.com/todos/4";

        given().when().get(url2).then().statusCode(200).body("title", equalTo("et porro tempora"));

        given().when().get(url).then().statusCode(200).body("id", equalTo(198)).body("id", equalTo(199));

        given().when().get(url).then().time(lessThan(2000L));
    }
} 