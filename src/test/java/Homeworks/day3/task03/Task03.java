package Homeworks.day3.task03;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class Task03 extends BaseUrlTask03 {
    /*
    Using the https://petstore.swagger.io/ document,
    write an automation test that finds the number of "pets" with the status "available"
    and asserts that there are more than 100.
     */

    @Test
    public void task03() {
        //Set the Url
        String url = "https://petstore.swagger.io/v2/pet/findByStatus?status=available";
        //spec.pathParams("first", "pet", "second", "findByStatus?status=available");

        //Set Expected Data

        //Send the request and get the response
        Response response = given().when().get(url);
        //Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Assertions
        assertEquals(200, response.statusCode());

        //asserts that there are more than 100
        JsonPath json = response.jsonPath();
        int availablePetsCount = json.getList("findAll { it.status == 'available' }").size();
        assertTrue(availablePetsCount > 100);
    }

}
