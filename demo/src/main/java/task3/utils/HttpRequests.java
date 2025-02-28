package task3.utils;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HttpRequests
{
    public static Response getRequest(String baseUri, String request)
    {
        return RestAssured.given()
                .baseUri(baseUri)
                .when()
                .get(request);
    }


    public static <T> T convertResponseToModel(Response response, Class<T> modelType)
    {
        return response.as(modelType);
    }


    public static Response postRequest(String baseUri, String request, String requestBody, String bodyContentType)
    {
        return RestAssured.given()
                .baseUri(baseUri)
                .contentType(bodyContentType)
                .body(requestBody)
                .when()
                .post(request);
    }


    public static <T> List<T> convertResponseToListOfModels(Response response, String jsonPath, Class<T> modelType)
    {
        return response.jsonPath()
                .getList(jsonPath, modelType);
    }
}
