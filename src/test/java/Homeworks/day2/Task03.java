package Homeworks.day2;

import base_urls.Hw_BaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Task03 extends Hw_BaseUrl {
    /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then
            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */
    @Test
    public void task03() {
        //Set the Url
        spec.pathParams("first", "api", "second", "unknown");

        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Assertions

        //1)Status code is 200
        response.then().statusCode(200);

        //2)Print all pantone_values
        JsonPath json = response.jsonPath();
        List<Objects> all_pantone_values = json.getList("data.pantone_value");
        //System.out.println("all_pantone_values = " + all_pantone_values);

        //3)Print all ids greater than 3 on the console
        List<Integer> ids = json.getList("data.findAll{it.id>3}.id");
        //List<Integer> ids = json.getList("data.id");
        //ids = ids.stream().filter(t -> t > 3).toList();
        System.out.println("ids = " + ids);
        //Assert that there are 3 ids greater than 3
        int numOfIds = ids.size();
        assertEquals(3, numOfIds);

        //4)Print all names whose ids are less than 3 on the console
        List<String> names = json.getList("data.findAll{it.id<3}.name");
        System.out.println("names = " + names);
        //Assert that the number of names whose ids are less than 3 is 2
        int nameList = names.size();
        assertEquals(2, nameList);
    }

}
