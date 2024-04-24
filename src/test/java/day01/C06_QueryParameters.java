package day01;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertTrue;

public class C06_QueryParameters {
     /*
    Given
       https://restful-booker.herokuapp.com/booking
    When
       User sends a GET request to the URL
    Then
       Status code is 200
    And
       Among the data, there should be someone whose first name is "John" and last name is "Smith"
*/

    @Test
    public void queryParametersTest() {

//        1. Set the URL
        String url = "https://restful-booker.herokuapp.com/booking?firstname=John&lastname=Smith";
//        2. Set the expected data

//        3. Send the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();

//        4. Do Assertion

        //1st way:
        //use
        // response.then().body("bookingid",hasSize(greaterThan(0)));
        //response.then().body(containsString("bookingid"));
        response.then()
                .statusCode(200)
                .body("[0]", notNullValue())
                .body(containsString("bookingid"))
                .body("bookingid", hasSize(greaterThan(0)));
        //2nd way:
        //change response to String and play with it
        String responseString = response.asString();
        assertTrue(responseString.contains("bookingid"));
    }
}