package stepDefinitions;
import com.google.gson.Gson;
import commonCode.ScenarioContext;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response ;
import io.restassured.specification.RequestSpecification;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pojos.Students;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentsGetAPIs {
    String baseUrl ="http://localhost:8086/student";
    ScenarioContext context = new ScenarioContext();
    String stuListUrl= baseUrl+ "/list";
    String createStuUrl= baseUrl+ "/student";

    @Given("I have all required data for API call")
    public void i_have_all_required_data_for_api_call() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I am in Given step");
        RequestSpecification reqSep=RestAssured.given() ;
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
            res.prettyPrint();
        try {
            int id_size = res.jsonPath().getList("id").size();
            List<String> nameList = res.jsonPath().getList("firstName");
            int index_cullen =  res.jsonPath().getList("firstName").indexOf("Cullen");
            for (String name : nameList) {
               System.out.println("Student name is:" + name);
            }
            System.out.println("Total no. of students in response is:" + id_size);
            Assert.assertEquals(id_size,108);
            System.out.println("Total no. of students name in response is:" + nameList.size());
            System.out.println("Index for student name Cullen:" + index_cullen);
            Assert.assertEquals(index_cullen,4);

            //find all unique courses from the response
            List<List<String>> coursesList = res.jsonPath().getList("courses");
            Set unique_Courses = new HashSet<String>();
            for(List <String> courses :coursesList){
                for (String el:courses){
                unique_Courses.add(el);
                }
            }
            System.out.println(unique_Courses);
            //find all student firstname that contains programme as Financial Analysis
            List<String> stuFinProgm = res.jsonPath().getList("findAll{it.programme.contains('Financial Analysis')}.firstName");
            System.out.println(stuFinProgm.toString());
            //find all student name that contains course Java

        }catch (NullPointerException e){
            System.out.println("Null pointer exception caught");
        }

        }

    @Given("I have {string} {string} {string} {string} {string} data API payload")
    public void iHaveDataAPIPayload(String fName, String lName, String email, String program, String courses) {
        Students stuPayload= new Students();
        stuPayload.setfName(fName);
        stuPayload.setLName(lName);
        stuPayload.setEmail(email);
        stuPayload.setProgramme(program);
        String arr_courses[] =courses.split(",");
        stuPayload.setCourses(arr_courses);
        Gson gson=new Gson();
       String payload =gson.toJson(stuPayload);
       System.out.println("payload:"+payload);
       context.setContext("payload",payload);
        RequestSpecification reqSep= RestAssured.given().contentType(ContentType.JSON);
        context.setContext("request",reqSep);
    }
    @When("I call create students api")
    public void i_call_create_students_api() {
        String payload = (String) context.getContext("payload");
        RequestSpecification req= (RequestSpecification) context.getContext("request");
        System.out.println(baseUrl);
        Response res= req.when().body(payload).post(baseUrl);
        res.prettyPrint();
        context.setContext("response",res);
    }
    @Then("I get response status as {int} with response body contains success message")
    public void i_get_response_status_as_with_response_body_contains_success_msg(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I am in Then step");
        Response res = (Response)context.getContext("response");
        Assert.assertEquals(res.statusCode(), 201);
        Assert.assertEquals(res.jsonPath().get("msg"), "Student added");

    }


//    @Given("I have {string} {string} {string} {string} {string} data API payload")
//    public void iHaveDataAPIPayload(String arg0, String arg1, String arg2, String arg3, String arg4) {
//    }
}
