package day7;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class C32_GetBooking extends PetStoreBaseUrl {
    /*
    Given
        url: "https://restful-booker.herokuapp.com/booking/:id

    When
        user send GET request
    Then
        verify status code is 200
    And
        response is like :
        {
    "bookingid": 1,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}

     */

    @Test(dependsOnMethods = {"day7.C31_CreateBooking.CreateBookingTest"})
    public void getBookingTest() {

        //Set the url
        spec.pathParams("first", "booking", "second", C31_CreateBooking.bookingId);

        //Set Expected Data
        String expectedStr = """
                {
                    "firstname": "Mohammed",
                    "lastname": "Alqahtani",
                    "totalprice": 505,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Breakfast"
                }""";

        BookingPojo expectedData = convertJsonToJava(expectedStr, BookingPojo.class);

        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();


        BookingPojo actualData = convertJsonToJava(response.asString(), BookingPojo.class);


        //Do assertion
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

    }
}
