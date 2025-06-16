package commonCode;

import io.restassured.RestAssured;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RestClient{

    RequestSpecification reqSpec;
    Response res;
    public Response doGetCall(String url){
        reqSpec = RestAssured.given();
        res= reqSpec.when().log().all().get(url);
        return res;
    }

    public Response doGetwPathParam(String url, String pathParam, String pathParamVal){
        HashMap<String,String> pathParamMap = new HashMap<String, String>();
        pathParamMap.put(pathParam,pathParamVal);
        reqSpec = RestAssured.given().pathParams(pathParamMap);
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