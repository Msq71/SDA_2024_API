package day05;

import base_urls.GorestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class C27_RevisionExample2 extends GorestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Abhaidev Kaur", "Fr. Deenabandhu Adiga", "Akshita Singh DC" are among the users -> Data may change
        And
            The female users are less than or equals to male users -> Data may change
*/
    @Test
    public void test() {

        //Set the Url
        spec.pathParams("first", "users");
        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).when().get("{first}");
        //response.prettyPrint();

        //Do assertion

        //The value of "pagination limit" is 10
        response
                .then()
                .body("meta.pagination.limit", equalTo(10));

        /////////////////////////////////////////////////////////////////////////////////////////

        //The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        response
                .then()
                .body("meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"));

        //////////////////////////////////////////////////////////////////////////////////////////

        //The number of users should  be 10
        JsonPath json = response.jsonPath();
        List<String> names = json.getList("data.name");
        //System.out.println("names = " + names);
        int numOfUsers = names.size();
        assertEquals(10, numOfUsers);

        /////////////////////////////////////////////////////////////////////////////////////////

        // We have at least one "active" status
        List<String> status = json.getList("data.findAll{it.status=='active'}");
        int numOfActive = status.size();
        assertTrue(numOfActive >= 1);

        /////////////////////////////////////////////////////////////////////////////////////////

        //"Abhaidev Kaur", "Fr. Deenabandhu Adiga", "Akshita Singh DC" are among the users -> Data may change
        assertTrue(names.contains("Draupadi Singh"));
        assertTrue(names.contains("Radha Mukhopadhyay"));
        assertTrue(names.contains("Devagya Joshi"));

        /////////////////////////////////////////////////////////////////////////////////////////

        //The female users are less than or equals to male users -> Data may change
        List<String> females = json.getList("data.findAll{it.gender=='female'}");
        List<String> males = json.getList("data.findAll{it.gender=='male'}");
        //System.out.println("females = " + females);
        //System.out.println("males = " + males);
        int numOfFemales = females.size();
        int numOfMales = females.size();
        assertTrue(numOfFemales >= numOfMales);
    }
}
