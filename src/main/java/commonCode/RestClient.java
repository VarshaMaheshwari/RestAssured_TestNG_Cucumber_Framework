package commonCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class RestClient{

    RequestSpecification reqSpec;
    Response res;
    public Response doGetCall(String url){
        reqSpec = RestAssured.given();
        res= reqSpec.when().log().all().get(url);
        return res;
    }

    public Response doGetwPathParam(String url, Map<String,String> pathParamMap){
        reqSpec = RestAssured.given().pathParams(pathParamMap);
         res = reqSpec.when().log().all().get(url);
        return res;
    }

    public Response doGetwQueryParam(String url, Map<String,String> headersMap, Map<String, Integer> queryParamMap){
        reqSpec = RestAssured.given().headers(headersMap).queryParams(queryParamMap);
        res = reqSpec.when().log().all().get(url);
        return res;
    }

    public Response doPostCall(String url, Object payload){
        reqSpec = RestAssured.given();
        res = reqSpec.when().log().all().body(payload).post(url);
        return res;
    }

    public Response doPutCall(String url, Object payload){
        reqSpec = RestAssured.given();
        res = reqSpec.when().log().all().body(payload).put(url);
        return res;
    }

    public Response doDeleteCall(String url){
        reqSpec= RestAssured.given();
        res = reqSpec.when().delete();
        return res;
    }

}