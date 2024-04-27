package Homeworks.day3.task01;

import base_urls.Hw_BaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Task01 extends Hw_BaseUrl {
    /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */
    @Test
    public void task01() {
        //Set the Url
        spec.pathParams("first", "api", "second", "users");

        //Set Expected Data
        JsonPlaceHolderPojoTask01 payLoad = new JsonPlaceHolderPojoTask01("morpheus", "leader");
        System.out.println(payLoad);

        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().post("{first}/{second}");
        response.prettyPrint();

        //Assertions
        JsonPlaceHolderPojoTask01 actualData = response.as(JsonPlaceHolderPojoTask01.class);
        assertEquals(201, response.statusCode());
        assertEquals(payLoad.getName(), actualData.getName());
        assertEquals(payLoad.getJob(), actualData.getJob());

    }

}
