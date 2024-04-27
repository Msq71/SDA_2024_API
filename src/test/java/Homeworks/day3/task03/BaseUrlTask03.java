package Homeworks.day3.task03;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class BaseUrlTask03 {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
                .setContentType(ContentType.JSON)
                .build();
    }
}