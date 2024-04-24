package day02;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class C09_GroovyLanguage extends JsonPlaceHolderBaseUrl {

     /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
         I send GET Request to the URL
    Then
         1)Status code is 200
         2)Print all ids greater than 190 on the console
           Assert that there are 10 ids greater than 190
         3)Print all 'completeds' whose ids are less than 5 on the console
           Assert that the number of userIds whose ids are less than 5 is 4
         4)Print all titles whose ids are greater than 195
           Assert that "quis eius est sint explicabo" is one of the titles whose id is less than 5
         5)Print id whose title is "quo adipisci enim quam ut ab"
           Assert that id is 8
*/

    @Test
    public void groovyTest() {

        //Set the Url
        spec.pathParams("first", "todos");
        //Set the expected data

        //Send the request and get the response
        Response respone = given(spec).when().get("{first}");
        //respone.prettyPrint();
        //Do assertion

//        1)Status code is 200
        respone.then().statusCode(200);
//        2)Print all ids greater than 190 on the console
        JsonPath json = respone.jsonPath();

        List<Integer> idList = json.getList("id");
        System.out.println("idList = " + idList);

        int idGreaterThan190 = 0; //flag
        for (Integer w : idList) {
            if (w > 190) {
                System.out.println(w);
                idGreaterThan190++;
            }
        }
//        Assert that there are 10 ids greater than 190
        assertEquals(10, idGreaterThan190);

//        3)Print all 'completeds' whose ids are less than 5 on the console
        List<Boolean> completedLessThan5 = json.getList("findAll{it.id<5}.completed");
        //findAll{it.id<5 && !it.completed}.collect{it.completed}
        System.out.println("completedLessThan5 = " + completedLessThan5);
//        Assert that the number of 'completeds' whose ids are less than 5 is 4
        assertEquals(4, completedLessThan5.size());


//        4)Print all titles whose ids are greater than 195
        List<String> titlesIdGreaterThan195 = json.getList("findAll{it.id>195}.title");
        System.out.println("titlesIdGreaterThan195 = " + titlesIdGreaterThan195);
//        Assert that "quis eius est sint explicabo" is one of the titles whose id is less than 5
        String expectedTitle = "quis eius est sint explicabo";
        assertTrue(titlesIdGreaterThan195.contains(expectedTitle));

//        5)Print id whose title is "quo adipisci enim quam ut ab"
        //Object id = json.getList("findAll{it.title=='quo adipisci enim quam ut ab'}.id").get(0);
        List<Integer> idList2 = json.getList("findAll{it.title=='quo adipisci enim quam ut ab'}.id");
        System.out.println("id = " + idList2);

//        Assert that id is 8
        int id = idList2.get(0);
        System.out.println("id = " + id);
        assertEquals(8, id);


    }
}
