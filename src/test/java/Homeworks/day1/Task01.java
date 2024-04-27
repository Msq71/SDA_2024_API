package Homeworks.day1;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Task01 {
    /*
        Given
            https://reqres.in/api/users/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */
    @Test
    public void task01() {
        // 1) Set Url
        String url = "https://reqres.in/api/users/3";
        // 2) Set expected data (if we expect data in certain format) or payload (if we use put or post)

        // 3) Sent request and get response
        Response response = given().when().get(url);

        // 4) Do assertions
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .statusLine("HTTP/1.1 200 OK");
    }

}
