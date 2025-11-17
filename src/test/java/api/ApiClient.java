package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import static io.restassured.RestAssured.given;

@AllArgsConstructor
public class ApiClient {

    public <T> Response post(String path, T body) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(RestAssured.baseURI)
                .contentType("application/json")
                .body(body)
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

    public Response get(String path, String queryParam) {
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

}
