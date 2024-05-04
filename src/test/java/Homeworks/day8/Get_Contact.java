package Homeworks.day8;

import base_urls.ContactBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import pojos.ContactPojo;
import pojos.ContactResponsePojo;

import static Homeworks.day8.Add_Contact.contactId;
import static Homeworks.day8.Add_Contact.contactOwner;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class Get_Contact extends ContactBaseUrl {
    /*
    Given
        url: https://thinking-tester-contact-list.herokuapp.com/contacts/

    When
        user send GET request
    Then
        verify status code is 200
    And
        response is like :
       {
         "_id": "663673570a93760013f0d19f",
            "firstName": "Mohammed",
            "lastName": "Alqahtani",
            "country": "KSA",
            "birthdate": "2000-10-06",
            "phone": "8005555555",
            "city": "Anytown",
            "postalCode": "12345",
            "stateProvince": "KS",
            "street1": "1 Main St.",
            "street2": "Apartment A",
            "email": "msqx71@gmail.com",
            "owner": "65df2d3aeb47a50013913611",
            "__v": 0
        }
*/
    @Test(dependsOnMethods = {"Homeworks.day8.Add_Contact.AddContactTest"})
    public void GetContactTest() {

        //Set the url
        spec.pathParam("first", "contacts");

        //Set Expected Data
        String expectedStr = "{\n" +
                "    \"_id\": \"" + contactId + "\",\n" +
                "    \"firstName\": \"Mohammed\",\n" +
                "    \"lastName\": \"Alqahtani\",\n" +
                "    \"country\": \"KSA\",\n" +
                "    \"birthdate\": \"2000-10-06\",\n" +
                "    \"phone\": \"8005555555\",\n" +
                "    \"city\": \"Anytown\",\n" +
                "    \"postalCode\": \"12345\",\n" +
                "    \"stateProvince\": \"KS\",\n" +
                "    \"street1\": \"1 Main St.\",\n" +
                "    \"street2\": \"Apartment A\",\n" +
                "    \"email\": \"msqx71@gmail.com\",\n" +
                "    \"owner\": \"" + contactOwner + "\",\n" +
                "    \"__v\": 0\n" +
                "}";

        ContactResponsePojo expectedData = convertJsonToJava(expectedStr, ContactResponsePojo.class);

        //Send the request and get the response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();


        //Do assertion
        assertEquals(200, response.statusCode());
    }
}
