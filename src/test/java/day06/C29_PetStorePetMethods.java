package day06;

import base_urls.RestFullBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.PetStore.PetStoreResponse;

import static io.restassured.RestAssured.given;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class C29_PetStorePetMethods extends RestFullBaseUrl {
    /*
    //Write an automation test that will create, read, update, delete a 'pet' using the
    "https://petstore.swagger.io/" document
    (All actions must be done on same pet)
    (Use Pojo)
     */
    int id = 505;
    PetStoreResponse payLoad;

    @Test
    public void createPetTest() {
        //Set the url
        spec.pathParam("first", "pet");


        //Set Expected Data (Payload)
        String payLoadStr = """
                {
                    "id": 505,
                    "category": {
                        "id": 3,
                        "name": "Doggie"
                    },
                    "name": "Aldo",
                    "photoUrls": [
                        "string",
                        "another"
                    ],
                    "tags": [
                        {
                            "id": 1,
                            "name": "Cute"
                        },
                        {
                            "id": 2,
                            "name": "Cheap"
                        }
                    ],
                    "status": "available"
                }""";

        payLoad = convertJsonToJava(payLoadStr, PetStoreResponse.class);

        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        response.then().statusCode(200);
    }

    @Test(dependsOnMethods = "createPetTest", priority = 1)
    public void getPetTest() {

        //Set the url
        spec.pathParams("first", "pet", "second", id);

        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

    }

    @Test(dependsOnMethods = "createPetTest", priority = 2)
    public void updatePetTest() {

        //Set the url
        spec.pathParams("first", "pet");

        //Set Expected Data
        payLoad.setName("Aslan");
        payLoad.getTags().get(0).setName("Sick");
        payLoad.setStatus("pending");

        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().put("{first}");
        response.prettyPrint();
    }

    @Test(dependsOnMethods = "createPetTest", priority = 3)
    public void DeletePetTest() {

        //Set the url
        spec.pathParams("first", "pet", "second", id);

        //Set Expected Data
        payLoad.setName("Aslan");
        payLoad.getTags().get(0).setName("Sick");
        payLoad.setStatus("pending");

        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().delete("{first}/{second}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

}


