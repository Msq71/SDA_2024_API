package base_urls;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

public class PetStoreBaseUrl {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setUp() {

        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();
    }
}