package day7;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class C31_CreateBooking extends PetStoreBaseUrl {


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

    public static int bookingId;
    static BookingPojo payload;

    @Test
    public void CreateBookingTest() {

        //Set the url
        spec.pathParam("first", "booking");

        //Set Expected Data
        String payloadStr = """
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

        payload = convertJsonToJava(payloadStr, BookingPojo.class);

        //Send the request and get the response
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();


        //Do assertion
        BookingResponsePojo actualData = convertJsonToJava(response.asString(), BookingResponsePojo.class);


        assertEquals(200, response.statusCode());
        assertEquals(payload.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(payload.getLastname(), actualData.getBooking().getLastname());
        assertEquals(payload.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(payload.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(payload.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(payload.getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payload.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

        bookingId = actualData.getBookingid();
        System.out.println("bookingId = " + bookingId);


    }
    
}


