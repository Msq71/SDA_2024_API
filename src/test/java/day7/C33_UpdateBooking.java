package day7;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class C33_UpdateBooking extends PetStoreBaseUrl {

     /*
   Given
       1) https://restful-booker.herokuapp.com/booking:id
       body:
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
       I send PUT Request to the Url
   Then
       Status code is 200
       And response body should be like
       {
                "firstname": "Abdullah",
                "lastname": "Alqahtani",
                "totalprice": 505,
                "depositpaid": true,
                "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Lunch"
        }
*/

    @Test(dependsOnMethods = {"day7.C31_CreateBooking.CreateBookingTest"})
    public void updateBookingTest() {

        //Set the url
        spec.pathParams("first", "booking", "second", C31_CreateBooking.bookingId);

        //Set Expected Data

         //payload = convertJsonToJava(payloadStr, BookingPojo.class);

        C31_CreateBooking.payload.setFirstname("Abdullah");
        C31_CreateBooking.payload.setAdditionalneeds("Lunch");
        //System.out.println("payload = " + payload);

        //Send the request and get the response
        Response response = given(spec).body(C31_CreateBooking.payload).when().put("{first}/{second}");
        response.prettyPrint();


        //Do assertion
        BookingPojo actualData = convertJsonToJava(response.asString(), BookingPojo.class);


        assertEquals(200, response.statusCode());
        AssertJUnit.assertEquals(C31_CreateBooking.payload.getFirstname(), actualData.getFirstname());
        AssertJUnit.assertEquals(C31_CreateBooking.payload.getLastname(), actualData.getLastname());
        AssertJUnit.assertEquals(C31_CreateBooking.payload.getTotalprice(), actualData.getTotalprice());
        AssertJUnit.assertEquals(C31_CreateBooking.payload.getDepositpaid(), actualData.getDepositpaid());
        AssertJUnit.assertEquals(C31_CreateBooking.payload.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        AssertJUnit.assertEquals(C31_CreateBooking.payload.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
        AssertJUnit.assertEquals(C31_CreateBooking.payload.getAdditionalneeds(), actualData.getAdditionalneeds());

    }
}
