package day02;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;

public class StaticImportBaseURI {
    /*
Given Send GET request by adding static import to the class
Then assert that status code is 200
And assert that status line is HTTP/1.1 200 OK
 */
    @Test
    void test() {
        // https://reqres.in/api/users?page=2
        Response response = get("https://reqres.in/api/users?page=2");
        response
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");

    }
}
