package day7;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static day7.C31_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C36_GetBookingNegative extends PetStoreBaseUrl {
     /*
    Given
        url: "https://restful-booker.herokuapp.com/booking/:id
    When
        user send GET request
    Then
        verify status code is 200
    And
        response is like :

     */

    @Test(dependsOnMethods = {"day7.C31_CreateBooking.CreateBookingTest", "day7.C35_DeleteBooking.deleteBookingTest"})
    public void getBookingTest() {
        // Set Url
        spec.pathParams("first", "booking"
                , "second", bookingId);

        // Set Expected Data


        // Send request and response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        assertEquals(404, response.statusCode());
        assertEquals("Not Found", response.asString());


    }
}

