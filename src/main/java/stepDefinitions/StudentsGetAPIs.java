package stepDefinitions;
import commonCode.ScenarioContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response ;
import io.restassured.specification.RequestSpecification;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class StudentsGetAPIs {
    String baseUrl ="http://localhost:8086/student";
    ScenarioContext context = new ScenarioContext();
    String stuListUrl= baseUrl+ "/list";
    @Given("I have all required data for API call")
    public void i_have_all_required_data_for_api_call() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I am in Given step");
        RequestSpecification reqSep=RestAssured.given().contentType(ContentType.JSON) ;
        context.setContext("request",reqSep);

    }

    @When("I call get all students api")
    public void i_call_get_all_students_api() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I am in When step");
        RequestSpecification req= (RequestSpecification) context.getContext("request");
        System.out.println(stuListUrl);
        Response res= req.when().get(stuListUrl );
        context.setContext("response",res);
    }


    @Then("I get response status as {int} with valid response body")
    public void i_get_response_status_as(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I am in Then step");
            Response res= (Response)context.getContext("response");
//            res.prettyPrint();
            int id_size=res.jsonPath().getList("id").size();
            List<String> nameList=res.jsonPath().getList("firstName");
            for(String name :nameList){
                System.out.println("Student name is:"+ name);
            }
            System.out.println("Total no. of students in response is:"+ id_size);
            System.out.println("Total no. of students name in response is:"+ nameList.size());


        }


}
