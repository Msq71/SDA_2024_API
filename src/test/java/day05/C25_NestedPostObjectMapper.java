package day05;

import base_urls.PetStoreBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class C25_NestedPostObjectMapper extends PetStoreBaseUrl {
     /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
               "firstname": "Jane",
               "lastname": "Doe",
               "totalprice": 111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2018-01-01",
                   "checkout": "2019-01-01"
               },
               "additionalneeds": "Extra pillows please"
           }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like
       {
           "bookingid": 2243,
           "booking":{
                       "firstname": "Jane",
                       "lastname": "Doe",
                       "totalprice": 111,
                       "depositpaid": true,
                       "bookingdates": {
                           "checkin": "2018-01-01",
                           "checkout": "2019-01-01"
                       },
                       "additionalneeds": "Extra pillows please"
                   }
            }
*/

    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "booking");

        //Set Expected Data
        String expectedStr = """
                {
                               "firstname": "Jane",
                               "lastname": "Doe",
                               "totalprice": 111,
                               "depositpaid": true,
                               "bookingdates": {
                                   "checkin": "2018-01-01",
                                   "checkout": "2019-01-01"
                               },
                               "additionalneeds": "Extra pillows please"
                           }""";

        ObjectMapper objectMapper = new ObjectMapper();
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
