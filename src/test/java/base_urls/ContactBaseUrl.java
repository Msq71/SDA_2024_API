package base_urls;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import static utilities.AuthenticationContact.generateToken;
import static utilities.AuthenticationRestful.getToken;

public class ContactBaseUrl {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setUp() {

        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com/")
                .addHeader("Authorization", "Bearer " + generateToken())
                .build();


    }
}