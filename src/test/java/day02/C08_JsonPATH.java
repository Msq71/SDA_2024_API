package day02;

import base_urls.RestFullBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class C08_JsonPATH extends RestFullBaseUrl {
     /*
    Given
        https://restful-booker.herokuapp.com/booking/23
    When
        User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is "application/json"
    And
        Response body should be like;
        {
            "firstname": "Josh",
            "lastname": "Allen",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "super bowls"
        }
*/

    @Test
    public void jsonPathTest() {

        //Set the Url
        spec.pathParams("first", "booking", "second", "23");
        //Set the expected data


        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1st way: then() method with hamcrest matchers
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Bob"))
                .body("lastname", equalTo("Smith"))
                .body("totalprice", equalTo(111))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2018-01-01"))
                .body("bookingdates.checkout", equalTo("2019-01-01"))
                .body("additionalneeds", equalTo("Breakfast"));


        //2nd way: By extracting data outside the body with JSONPath

        //Convert Response to JsonPath object
        JsonPath json = response.jsonPath();

        //Retrieve the desired data by using JsonPath object
        String firstname = json.getString("firstname");
        String lastname = json.getString("lastname");
        int totalPrice = json.getInt("totalprice");
        boolean depositPaid = json.getBoolean("depositpaid");
        String checkinDate = json.getString("bookingdates.checkin");
        String checkoutDate = json.getString("bookingdates.checkout");
        String additionalNeeds = json.getString("additionalneeds");

        assertEquals("Bob", firstname);
        assertEquals("Smith", lastname);
        assertEquals(111, totalPrice);
        assertEquals(true, depositPaid);
        assertEquals("2018-01-01", checkinDate);
        assertEquals("2019-01-01", checkoutDate);
        assertEquals("Breakfast", additionalNeeds);


        //If this assertion fails, the subsequent lines won't execute. Because this is Hard Assertion.


        //How to use Soft Assertion
        //1st step: Create SoftAssert object
        SoftAssert softAssert = new SoftAssert();
        //2nd step: Do assertion
        softAssert.assertEquals("Bob", firstname);
        softAssert.assertEquals("Smith", lastname);
        softAssert.assertEquals(true, depositPaid);
        softAssert.assertEquals("2018-01-01", checkinDate);
        softAssert.assertEquals("2019-01-01", checkoutDate);
        softAssert.assertEquals("Breakfast", additionalNeeds);

        //If this assertion fails, the subsequent lines will execute as well. Because this is Soft Assertion.


        //3rd step: Use assertAll() method.
        softAssert.assertAll();

        //Not: Hard assertion is used commonly in market. Because if any failure occurs we must fix it immediately.

    }
}
