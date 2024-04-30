package Homeworks.day5;

import Homeworks.day3.task01.JsonPlaceHolderPojoTask01;
import base_urls.RestFullBaseUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class CreateUser extends RestFullBaseUrl {
    /*
    //Write an automation test that will create a 'user'
     then read, update and delete the created user
      using the "https://petstore.swagger.io/" document.
      (Create a classes for each request.)
     */
    @Test
    public void createUser() {

        // Set Url
        spec.pathParam("first", "user");

        // Set Expected Data:
        /*
            {
            "code": 200,
            "type": "unknown",
            "message": "ok"
            }
         */
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

        ObjectMapper objectMapper = new ObjectMapper();
        UserPojo payLoad = convertJsonToJava(expectedStr, UserPojo.class);

        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        //Assertions
        assertEquals(200, response.statusCode());

    }
}
