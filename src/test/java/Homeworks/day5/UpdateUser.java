package Homeworks.day5;

import base_urls.RestFullBaseUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class UpdateUser extends RestFullBaseUrl {
/*
    //Write an automation test that will create a 'user'
     then read, update and delete the created user
      using the "https://petstore.swagger.io/" document.
      (Create a classes for each request.)
     */

    @Test()
    public void updatePetTest() {

        //Set the url
        spec.pathParams("first", "user", "second", "Msq71");

        String expectedStr = """
                {
                    "id": 24,
                    "username": "hhhh",
                    "firstName": "Mohammed",
                    "lastName": "Alqahtani",
                    "email": "m@gmail.com",
                    "password": "1234",
                    "phone": "1234",
                    "userStatus": 0
                  }""";

        ObjectMapper objectMapper = new ObjectMapper();
        UserPojo payLoad = convertJsonToJava(expectedStr, UserPojo.class);

        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().put("{first}/{second}");
        response.prettyPrint();

        //Assertions
        assertEquals(200, response.statusCode());

    }

}
