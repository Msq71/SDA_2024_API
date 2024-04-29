package day04;

import base_urls.JsonPlaceHolderBaseUrl;
import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class C21_DeleteRequest extends JsonPlaceHolderBaseUrl {

    /*
    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
        I send DELETE Request to the Url
    Then
        Status code is 200
        And Response body is { }
     */
    @Test
    public void test() {

        // Set Url
        spec.pathParams("first", "todos", "second", 198);

        // Set Expected data

        // Send request and get response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        // Do Assertions
        String responseStr = response.asString();

        assertEquals(200, response.statusCode());
        assertTrue(responseStr.equals("{}"));

    }


}