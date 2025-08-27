package stepDefinitions;

import commonCode.RestClient;
import commonCode.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.reporters.jq.INavigatorPanel;

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
        endpoint=(String) scenarioContext.getContext("url");
        System.out.println("I am in When step with url "+ endpoint);
        Response response= getHeadersAndQueryParam(page,per_page);
        scenarioContext.setContext("response", response);

    }

    @When("I invoke API request with  and {int}")
    public void i_invoke_api_request_with_default_page(Integer per_page) {
        endpoint=(String) scenarioContext.getContext("url");
        System.out.println("I am in When step with url "+ endpoint);
        Response response= getHeadersAndQueryParam(null,per_page);
        scenarioContext.setContext("response", response);
    }

    @When("I invoke API request with {int} and ")
    public void i_invoke_api_request_with_default_per_page(Integer page) {
        endpoint=(String) scenarioContext.getContext("url");
        System.out.println("I am in When step with url "+ endpoint);
        Response response= getHeadersAndQueryParam(page,null);
        scenarioContext.setContext("response", response);
    }
    @Then("I receive response code as {int}")
    public void i_receive_response_code_as(Integer statusCode) {
        System.out.println("I am in Then step with status code check");
        Response response = (Response) scenarioContext.getContext("response");
        Assert.assertEquals(response.statusCode(), statusCode);
        response.prettyPrint();

    }
    @Then("Response body has total as {int} and total_pages as {int}")
    public void response_body_has_total_as_and_total_pages_as(Integer total, Integer total_pages) {
        System.out.println("I am in Then step with total validation");
        Response response = (Response) scenarioContext.getContext("response");
        int actualTotal = response.jsonPath().getInt("total");
        int actualTotalPages = response.jsonPath().getInt("total_pages");
        Assert.assertEquals(actualTotal, total);
        Assert.assertEquals(actualTotalPages,total_pages);
    }
    @And("Response body contains data as per expected values {int}")
    public void response_body_contains_data_as_per_expected_values(Integer dataSize) {
        System.out.println("I am in Then step with total validation");
        Response response = (Response) scenarioContext.getContext("response");
        int actualDataSize=response.jsonPath().getList("data").size();
        Assert.assertEquals(actualDataSize, dataSize);

    }


    public Response getHeadersAndQueryParam(Integer page, Integer per_page){
        HashMap<String,Integer> queryParamMap = new HashMap<String, Integer>();
        if(page != null){
            queryParamMap.put("page", page);
        }
        else{
            queryParamMap.put("page", 1);
        }
        if(per_page != null){
            queryParamMap.put("per_page", per_page);
        }
        else{
            queryParamMap.put("per_page", 6);
        }

        HashMap<String,String> headersMap = new HashMap<String,String>();
        headersMap.put("accept", "application/json");
        headersMap.put("x-api-key","reqres-free-v1");
        Response response= restClient.doGetwQueryParam(endpoint,headersMap,queryParamMap);
        return response;
    }
}
