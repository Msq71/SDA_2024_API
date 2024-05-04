package day7;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class C34_PatchBooking extends PetStoreBaseUrl {
     /*
    Given
        url: "https://restful-booker.herokuapp.com/booking/:id
    And
        body:     {
                    "firstname" : "Tom",
                    "lastname" : "Hanks",
                }

    When
        user send patch request
    Then
        verify status code is 200
    And
        response is like :
                            {
                        "firstname" : "Tom",
                        "lastname" : "Hanks",
                        "totalprice" : 111,
                        "depositpaid" : true,
                        "bookingdates" : {
                            "checkin" : "2018-01-01",
                            "checkout" : "2019-01-01"
                        },
                        "additionalneeds" : "Lunch"
                    }
     */

    @Test(dependsOnMethods = {"day7.C31_CreateBooking.CreateBookingTest"})
    public void patchBookingTest() {

        //Set the url
        spec.pathParams("first", "booking", "second", C31_CreateBooking.bookingId);

        //Set Expected Data
        String payloadStr = """
                {
                    "firstname" : "Ahmed",
                    "lastname" : "Almansor"
                }""";

        //payload = convertJsonToJava(payloadStr, BookingPojo.class);

        Map<String, Object> payload = convertJsonToJava(payloadStr, Map.class);

        //Send the request and get the response
        Response response = given(spec).body(payload).when().patch("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = convertJsonToJava(response.asString(), Map.class);


        assertEquals(200, response.statusCode());
        assertEquals(payload.get("firstname"), actualData.get("firstname"));
        assertEquals(payload.get("lastname"), actualData.get("lastname"));


    }


}

