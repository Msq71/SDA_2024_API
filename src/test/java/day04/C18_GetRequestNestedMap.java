package day04;

import base_urls.PetStoreBaseUrl;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import testData.RestFullTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;


public class C18_GetRequestNestedMap extends PetStoreBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/827
    When
        I send GET Request to the url
    Then
        Status code should be 200
        Response body should be like that;
            {
                "firstname": "John",
                "lastname": "Smith",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
 */
    @Test
    public void test() {

        //Set the url:
        spec.pathParams("first", "booking", "second", "451");

        //Set the Expected Data:
        Map<String, Object> bookingDates = RestFullTestData.bookingDatesMapper("2018-01-01", "2019-01-01");

        Map<String, Object> expectedDate = RestFullTestData.bookingMapper("John", "Smith", 111, true, bookingDates, "Breakfast");

        //"John","Smith",  111 , true , bookingDates ,"Breakfast"

        // Send request and get response:
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do Assertions
        Map<String, Object> actualData = response.as(Map.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedDate.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedDate.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedDate.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedDate.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(bookingDates.get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingDates.get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedDate.get("additionalneeds"), actualData.get("additionalneeds"));


    }
}
