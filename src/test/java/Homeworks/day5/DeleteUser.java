package Homeworks.day5;

import base_urls.RestFullBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser extends RestFullBaseUrl {

    /*
    //Write an automation test that will create a 'user'
     then read, update and delete the created user
      using the "https://petstore.swagger.io/" document.
      (Create a classes for each request.)
     */
    @Test
    public void DeletePetTest() {

        //Set the url
        spec.pathParams("first", "user", "second", "Msq71");

        //Set Expected Data


        //Send the request and get the response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();
        response.then().statusCode(200);
    }
}
