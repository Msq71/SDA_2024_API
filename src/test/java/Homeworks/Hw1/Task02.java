package Homeworks.Hw1;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Task02 {
    /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty
     */
    @Test
    public void task02() {
        // 1) Set Url
        String url = "https://reqres.in/api/users/23";
        // 2) Set expected data (if we expect data in certain format) or payload (if we use put or post)

        // 3) Sent request and get response
        Response response = given().when().get(url);

        // 4) Do assertions
        response.then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .header("Server", "cloudflare")
                .body(equalTo("{}"));

        System.out.println(response.getBody().asString());
    }
}
