package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationContact {


    public static String generateToken() {//Contact List

        Map<String, String> mapBody = new HashMap<>();
        mapBody.put("email", "msqx71@gmail.com");
        mapBody.put("password", "1234567");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(mapBody)
                .post("https://thinking-tester-contact-list.herokuapp.com/users/login");
        return response.jsonPath().getString("token");
    }

}
