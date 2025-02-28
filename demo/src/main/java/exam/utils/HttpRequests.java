package exam.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HttpRequests {
    public static Response getRequest(Map<String, ?> headers, String baseUri, String request, Map<String, ?> queryParams) {
        return RestAssured.given()
                .headers(headers)
                .baseUri(baseUri)
                .queryParams(queryParams)
                .when()
                .get(request);
    }

    public static Response putRequest(Map<String, ?> headers, String url, File localFile) {
        try {
            return RestAssured.given()
                .headers(headers)
                .multiPart(localFile)
                .when()
                .put(new URL(url));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Response putRequest(Map<String, ?> headers, String url, String request, Map<String, ?> parameters) {
        return RestAssured.given()
            .headers(headers)
            .baseUri(url)
            .queryParams(parameters)
            .when()
            .put(request);
    }

    public static Response deleteRequest(Map<String, ?> headers, String url, String request, Map<String, ?> parameters) {
        return RestAssured.given()
            .headers(headers)
            .baseUri(url)
            .queryParams(parameters)
            .when()
            .delete(request);
    }

    public static Response postRequest(Map<String, ?> headers, String baseUri, String request, String requestBody, String bodyContentType) {
        return RestAssured.given()
                .headers(headers)
                .baseUri(baseUri)
                .contentType(bodyContentType)
                .body(requestBody)
                .when()
                .post(request);
    }

    public static Response postRequest(String baseUri, String request, String requestBody, String bodyContentType, Map<String, ?> queryParams) {
        return RestAssured.given()
                .baseUri(baseUri)
                .contentType(bodyContentType)
                .body(requestBody)
                .queryParams(queryParams)
                .when()
                .post(request);
    }

    public static Response postRequest(Map<String, ?> headers, String baseUri, String request, Map<String, ?> queryParams) {
        return RestAssured.given()
                .headers(headers)
                .baseUri(baseUri)
                .queryParams(queryParams)
                .when()
                .post(request);
    }

    public static Response postRequest(String baseUri, String request, Map<String, ?> queryParams) {
        return RestAssured.given()
                .baseUri(baseUri)
                .queryParams(queryParams)
                .when()
                .post(request);
    }

    public static <T> T convertResponseToModel(Response response, Class<T> modelType) {
        return response.as(modelType);
    }

    public static <T> List<T> convertResponseToListOfModels(Response response, String jsonPath, Class<T> modelType) {
        return response.jsonPath()
                .getList(jsonPath, modelType);
    }
}
