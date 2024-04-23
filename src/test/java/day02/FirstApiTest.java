package day02;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FirstApiTest {
    /*
    Given Send GET request to https://reqres.in/api/users?page=2
    Then print status code
    And print status line
     */
    @Test
    void test(){
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println("statusCode() = " + response.statusCode());
        System.out.println("statusLine() = " + response.statusLine());
    }
}
