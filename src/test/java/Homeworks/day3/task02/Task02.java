package Homeworks.day3.task02;

import base_urls.PetStoreBaseUrl;
import base_urls.RestFullBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;


public class Task02 extends RestFullBaseUrl {
    /*
    Write an automation test that will create a 'user' using the "https://petstore.swagger.io/" document
    */
    @Test
    public void task02() {
        //Set the Url
        spec.pathParam("first", "user");

        //Set Expected Data
        Map<String, Object> payLoad = new HashMap<>();
        payLoad.put("id", 505);
        payLoad.put("username", "Msq");
        payLoad.put("firstName", "Mohammed");
        payLoad.put("lastName", "Sultan");
        payLoad.put("email", "msqx71@gmail.com");
        payLoad.put("password", "123456");
        payLoad.put("phone", "050505050505");
        payLoad.put("userStatus", 1);
        System.out.println(payLoad);

        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        //Assertions
        assertEquals(200, response.statusCode());


    }
}
