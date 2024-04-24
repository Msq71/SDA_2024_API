package Homeworks.Hw2;

import base_urls.Hw2_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Task02 extends Hw2_BaseUrl {
    /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json; charset=utf-8"
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */
    @Test
    public void task02() {
        //Set url
        spec.pathParams("first", "api", "second", "unknown", "third", "3");

        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}/{third}");
        response.prettyPrint();

        //Assertions

        //1st way:
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id", equalTo(3)
                        , "data.name", equalTo("true red")
                        , "data.year", equalTo(2002)
                        , "data.color", equalTo("#BF1932")
                        , "data.pantone_value", equalTo("19-1664")
                        , "support.url", equalTo("https://reqres.in/#support-heading")
                        , "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!")
                );

        //2nd way:

        //Convert Response to JsonPath object
        JsonPath json = response.jsonPath();
        //Retrieve the desired data by using JsonPath object
        int id = json.getInt("data.id");
        String name = json.getString("data.name");
        int year = json.getInt("data.year");
        String color = json.getString("data.color");
        String pantone_value = json.getString("data.pantone_value");
        String url = json.getString("support.url");
        String text = json.getString("support.text");

        //Create SoftAssert object
        SoftAssert softAssert = new SoftAssert();
        //Do assertion
        softAssert.assertEquals(3, id);
        softAssert.assertEquals("true red", name);
        softAssert.assertEquals(2002, year);
        softAssert.assertEquals("#BF1932", color);
        softAssert.assertEquals("19-1664", pantone_value);
        softAssert.assertEquals("https://reqres.in/#support-heading", url);
        softAssert.assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", text);

        //Use assertAll() method.
        softAssert.assertAll();

    }
}
