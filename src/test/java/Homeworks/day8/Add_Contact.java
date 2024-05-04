package Homeworks.day8;

import base_urls.ContactBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.ContactPojo;
import pojos.ContactResponsePojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class Add_Contact extends ContactBaseUrl {
    /*
    //Write an automation test that will add a 'contact' then read,
     update and delete the created contact
      then negative assert the deleted contact
       using the "https://documenter.getpostman.com/view/4012288/TzK2bEa8" document.
     */

    //Add Contact:
    /*
   Given
       1) https://thinking-tester-contact-list.herokuapp.com/contacts
       2) {
            "firstName": "Mohammed",
            "lastName": "Alqahtani",
            "birthdate": "2000-10-06",
            "email": "msqx71@gmail.com",
            "phone": "8005555555",
            "street1": "1 Main St.",
            "street2": "Apartment A",
            "city": "Anytown",
            "stateProvince": "KS",
            "postalCode": "12345",
            "country": "KSA"
        }
   When
       I send POST Request to the Url
   Then
       Status code is 201
       And response body should be like
       {
        "_id": "6085a221fcfc72405667c3d4",
        "firstName": "Mohammed",
        "lastName": "Alqahtani",
        "birthdate": "2000-10-06",
        "email": "msqx71@gmail.com",
        "phone": "8005555555",
        "street1": "1 Main St.",
        "street2": "Apartment A",
        "city": "Anytown",
        "stateProvince": "KS",
        "postalCode": "12345",
        "country": "KSA",
        "owner": "6085a21efcfc72405667c3d4",
        "__v": 0
        }
*/

    static ContactPojo payload;
    public static String contactId;
    public static String contactOwner;

    @Test
    public void AddContactTest() {

        //Set the url
        spec.pathParam("first", "contacts");

        //Set Expected Data
        String payloadStr = """
                {
                            "firstName": "Mohammed",
                            "lastName": "Alqahtani",
                            "birthdate": "2000-10-06",
                            "email": "msqx71@gmail.com",
                            "phone": "8005555555",
                            "street1": "1 Main St.",
                            "street2": "Apartment A",
                            "city": "Anytown",
                            "stateProvince": "KS",
                            "postalCode": "12345",
                            "country": "KSA"
                        }""";

        payload = convertJsonToJava(payloadStr, ContactPojo.class);

        //Send the request and get the response
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();

        //Do assertion
        ContactResponsePojo actualData = convertJsonToJava(response.asString(), ContactResponsePojo.class);


        assertEquals(201, response.statusCode());
        assertEquals(payload.getFirstName(), actualData.getFirstName());
        assertEquals(payload.getLastName(), actualData.getLastName());
        assertEquals(payload.getCountry(), actualData.getCountry());
        assertEquals(payload.getBirthdate(), actualData.getBirthdate());
        assertEquals(payload.getPhone(), actualData.getPhone());
        assertEquals(payload.getCity(), actualData.getCity());
        assertEquals(payload.getPostalCode(), actualData.getPostalCode());

        contactId = actualData.get_id();
        contactOwner = actualData.getOwner();
        System.out.println("contactId = " + contactId);

    }
}
