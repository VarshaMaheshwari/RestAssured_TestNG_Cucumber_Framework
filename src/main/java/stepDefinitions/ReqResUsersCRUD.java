package stepDefinitions;

import commonCode.RestClient;
import commonCode.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;

public class ReqResUsersCRUD {

    String baseURL= "https://reqres.in/api";
    String userUrl= "/users";
    RestClient restClient= new RestClient();
    ScenarioContext scenarioContext = new ScenarioContext();
    String endpoint="";

    @Given("I have API request payload")
    public void i_have_api_request_payload() {
        System.out.println("I am in Given step");
        endpoint = baseURL+userUrl;
        scenarioContext.setContext("url",endpoint);
    }
    @When("I invoke API request with {int} and {int}")
    public void i_invoke_api_request(Integer page, Integer per_page) {
        System.out.println("I am in When step");
        endpoint=(String) scenarioContext.getContext("url");
        HashMap<String,Integer> queryParamMap = new HashMap<String, Integer>(page, per_page);
        Response response= restClient.doGetwQueryParam(endpoint,queryParamMap);
        scenarioContext.setContext("response", response);

    }
    @Then("I receive response code as {int}")
    public void i_receive_response_code_as(Integer statusCode) {
        System.out.println("I am in Then step with status code check");
        Response response = (Response) scenarioContext.getContext("response");
        Assert.assertEquals(response.statusCode(), statusCode);

    }
    @Then("Response body has total as {int} and total_pages as {int}")
    public void response_body_has_total_as_and_total_pages_as(Integer total, Integer total_pages) {
        System.out.println("I am in Then step with total validation");
        Response response = (Response) scenarioContext.getContext("response");
        int actualTotal = response.jsonPath().getInt("total");
        int actualTotalPages = response.jsonPath().getInt("total_pages");
        Assert.assertEquals(actualTotalPages, total);
        Assert.assertEquals(actualTotalPages,total_pages);
    }
    @Then("Response body contains data as per expected values")
    public void response_body_contains_data_as_per_expected_values() {
        System.out.println("I am in Then step with total validation");
        Response response = (Response) scenarioContext.getContext("response");
        int actualDataSize=response.jsonPath().getList("data").size();
        int expDataSize =
        Assert.assertEquals(actualDataSize, );

    }

}
