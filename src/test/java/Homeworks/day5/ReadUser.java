package Homeworks.day5;

import base_urls.RestFullBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class ReadUser extends RestFullBaseUrl {
    /*
   //Write an automation test that will create a 'user'
    then read, update and delete the created user
     using the "https://petstore.swagger.io/" document.
     (Create a classes for each request.)
    */
    @Test
    public void createUser() {

        // Set Url
        spec.pathParams("first", "user", "second", "Msq71");

        //Set Expected Data:
        String expectedStr = """
                {
                  "id": 12,
                  "username": "Msq71",
                  "firstName": "Mohammed",
                  "lastName": "Alqahtani",
                  "email": "m@gmail.com",
                  "password": "1234",
                  "phone": "1234",
                  "userStatus": 0
                }""";

        Map<String, Object> expectedData = convertJsonToJava(expectedStr, Map.class);

        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = convertJsonToJava(response.asString(), Map.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("username"), actualData.get("username"));
        assertEquals(expectedData.get("firstName"), actualData.get("firstName"));
        assertEquals(expectedData.get("email"), actualData.get("email"));
        assertEquals(expectedData.get("password"), actualData.get("password"));
        assertEquals(expectedData.get("phone"), actualData.get("phone"));
        assertEquals(expectedData.get("userStatus"), actualData.get("userStatus"));
    }
}
