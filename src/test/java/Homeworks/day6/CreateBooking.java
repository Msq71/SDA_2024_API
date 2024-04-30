package Homeworks.day6;

import base_urls.PetStoreBaseUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utilities.ObjectMapperUtilities;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class CreateBooking extends PetStoreBaseUrl {

    /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
                "firstname": "Mohammed",
                "lastname": "Alqahtani",
                "totalprice": 505,
                "depositpaid": true,
                "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Breakfast"
        }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like
       {
                "bookingid": 3774,
        "booking": {
        "firstname": "Mohammed",
        "lastname": "Alqahtani",
        "totalprice": 505,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}
*/
    @Test
    public void CreateBookingTest() {

        //Set the url
        spec.pathParam("first", "booking");

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

        BookingPojo payLoad = convertJsonToJava(expectedStr, BookingPojo.class);

        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        BookingResponsePojo actualData = convertJsonToJava(response.asString(), BookingResponsePojo.class);


        //Do assertion
        assertEquals(200, response.statusCode());
        assertEquals(payLoad.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(payLoad.getLastname(), actualData.getBooking().getLastname());
        assertEquals(payLoad.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(payLoad.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(payLoad.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(payLoad.getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());
    }



}


