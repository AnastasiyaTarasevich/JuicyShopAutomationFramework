package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import static io.restassured.RestAssured.given;

@AllArgsConstructor
public class ApiClient {

    public <T> Response post(String path, T body) {
        RequestSpecification request = given()
                .filter(new AllureRestAssured())
                .baseUri(RestAssured.baseURI)
                .contentType("application/json");

        if (body != null) {
            request.body(body);
        }

        return request
                .when()
                .post(path)
                .then()
                .log().ifError()
                .extract().response();
    }

    public <T> Response postWithAuth(String path, T body, String token) {
        RequestSpecification request = given()
                .filter(new AllureRestAssured())
                .baseUri(RestAssured.baseURI)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token);

        if (body != null) {
            request.body(body);
        }

        return request
                .when()
                .post(path)
                .then()
                .log().ifError()
                .extract().response();
    }

    public Response get(String path) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(RestAssured.baseURI)
                .when()
                .get(path)
                .then()
                .log().ifError()
                .extract().response();
    }

    public Response getWithAuth(String path, String token) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(RestAssured.baseURI)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(path)
                .then()
                .log().ifError()
                .extract().response();
    }

    public Response getWithQueryParam(String path, String queryParam) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(RestAssured.baseURI)
                .queryParam("q", queryParam)
                .when()
                .get(path)
                .then()
                .log().ifError()
                .extract().response();
    }

    public Response getWithPathParamAndAuth(String path, String id, String token) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(RestAssured.baseURI)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", id)
                .when()
                .get(path)
                .then()
                .log().ifError()
                .extract().response();
    }

    public Response delete(String pathTemplate, String token, int pathParam) {
        return given()
                .filter(new AllureRestAssured())
                .header("Authorization", "Bearer " + token)
                .baseUri(RestAssured.baseURI)
                .pathParam("basketItemId", pathParam)
                .when()
                .delete(pathTemplate)
                .then()
                .log().ifError()
                .extract()
                .response();
    }

}
