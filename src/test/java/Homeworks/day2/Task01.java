package Homeworks.day2;

import base_urls.Hw_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Task01 extends Hw_BaseUrl {
    /*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be "application/json"
       And
           "email" is "janet.weaver@reqres.in",
       And
           "first_name" is "Janet"
       And
           "last_name" is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */
    @Test
    public void task01() {
        //Set the Url
        spec.pathParams("first", "api", "second", "users", "third", "2");

        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}/{third}");
        response.prettyPrint();

        //Assertions

        //1st way:
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.email", equalTo("janet.weaver@reqres.in")
                        , "data.first_name", equalTo("Janet")
                        , "data.last_name", equalTo("Weaver")
                        , "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!")
                );

        //2nd way:
        //Convert Response to JsonPath object
        JsonPath json = response.jsonPath();
        //Retrieve the desired data by using JsonPath object
        String email = json.getString("data.email");
        String fname = json.getString("data.first_name");
        String lname = json.getString("data.last_name");
        String text = json.getString("support.text");
        //Assertions
        assertEquals("janet.weaver@reqres.in", email);
        assertEquals("Janet", fname);
        assertEquals("Weaver", lname);
        assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", text);
    }
}
