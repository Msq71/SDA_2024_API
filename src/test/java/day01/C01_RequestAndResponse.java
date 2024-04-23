package day01;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.http.Header;

import static io.restassured.RestAssured.given;

public class C01_RequestAndResponse {

    public static void main(String[] args) {
         /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends a GET Request to the url
    Then
        Print Status Code (should be 200)
    And
        Print Content Type (should be JSON)
    And
        Print Status Line (should be HTTP/1.1 200 OK)
    And
        Print Connection and Date headers on console
    And
        Print all headers on console

*/
        String url = "https://restful-booker.herokuapp.com/booking";
        Response response = given().get(url); //All data I need in response

        //How to print response
        System.out.println(response); // It prints reference of th response
        response.prettyPrint();

        //How to print status code
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);
        System.out.println("------------------------------------------------");
        //How to print Status Line
        String statusLine = response.statusLine();
        System.out.println("statusLine = " + statusLine);
        System.out.println("------------------------------------------------");
        //How to print Content Type
        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);
        System.out.println("------------------------------------------------");
        //How can I get Value from headers
        String conn = response.header("Connection");
        System.out.println("conn = " + conn);
        System.out.println("------------------------------------------------");
        String date = response.header("Date");
        System.out.println("Date = " + date);
        System.out.println("------------------------------------------------");
        //How to get all headers
        Headers headers = response.headers();
        System.out.println(headers);
        System.out.println("------------------------------------------------");
        //How to get response time
        System.out.println(response.time());

    }
}
