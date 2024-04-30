package day06;

import base_urls.DummyRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class C28_RevisionExample03 extends DummyRestBaseUrl {
    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends Get Request to the Url
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "Tatyana Fitzpatrick"
    And
        Total salary of all employees is 6,644,770
 */
    @Test
    public void test() {

        //Set the Url
        spec.pathParams("first", "employees");
        //*************************************************************************************//


        //Send the request and get the response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();
        //*************************************************************************************//

        //Do assertion

        //Status code is 200
        response.then().statusCode(200);
        //*************************************************************************************//

        //There are 24 employees

        //1st way:
        response.then().body("data.id", hasSize(24));

        //2nd way:
        JsonPath json = response.jsonPath();
        int numOfEmployees = json.getList("data.id").size();
        assertEquals(24, numOfEmployees);
        //*************************************************************************************//

        //"Tiger Nixon" and "Garrett Winters" are among the employees

        //1st way:
        response.then().body("data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"));

        //2nd way:
        List<String> nameList = json.getList("data.employee_name");
        assertTrue(nameList.contains("Tiger Nixon"));
        assertTrue(nameList.contains("Garrett Winters"));
        //*************************************************************************************//

        //The greatest age is 66

        List<Integer> ageList = json.getList("data.employee_age");
        int maxAge = 0;
        for (int age : ageList) {
            if (age > maxAge) {
                maxAge = age;
            }
        }
        assertEquals(66, maxAge);
        //*************************************************************************************//

        //The name of the lowest age is "Tatyana Fitzpatrick"
        int minAge = 100;
        for (int age : ageList) {
            if (age < minAge) {
                minAge = age;
            }
        }
        Object youngestEmployee = json.getList("data.findAll{it.employee_age==" + minAge + "}.employee_name").get(0);
        assertEquals("Tatyana Fitzpatrick", youngestEmployee);
        //*************************************************************************************//

        //Total salary of all employees is 6,644,770
        List<Integer> salaries = json.getList("data.employee_salary");
        int totalSalary = 0;
        for (int salary : salaries) {
            totalSalary += salary;
        }
        assertEquals(6644770, totalSalary);
        //*************************************************************************************//

    }

}
