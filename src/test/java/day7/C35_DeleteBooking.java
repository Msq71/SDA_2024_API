package day7;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C35_DeleteBooking extends PetStoreBaseUrl {
    /*
Given
    url: "https://restful-booker.herokuapp.com/booking/:id
When
    user send DELETE request
Then
    verify status code is 201
And
    response is like : Created
 */
    @Test(dependsOnMethods = {"day7.C31_CreateBooking.CreateBookingTest"})
    public void deleteBookingTest() {

        //Set the url
        spec.pathParams("first", "booking", "second", C31_CreateBooking.bookingId);

        //Set Expected Data
        String expectedStr = "Created";

        //Send the request and get the response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(201, response.statusCode());
        assertEquals(expectedStr, response.asString());

    }
}
