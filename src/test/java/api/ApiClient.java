package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class ApiClient {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String JSON = "application/json";

    private RequestSpecification baseRequest() {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(RestAssured.baseURI)
                .contentType(JSON);
    }

    private RequestSpecification baseRequestWithAuth(String token) {
        return baseRequest()
                .header(AUTH_HEADER, BEARER + token);
    }

    private Response extract(Response response) {
        return response.then()
                .log().ifError()
                .extract()
                .response();
    }

    // ---------- POST ----------

    public <T> Response post(String path, T body) {
        RequestSpecification request = baseRequest();
        if (body != null) {
            request.body(body);
        }
        return extract(request.post(path));
    }

    public <T> Response postWithAuth(String path, T body, String token) {
        RequestSpecification request = baseRequestWithAuth(token);
        if (body != null) {
            request.body(body);
        }
        return extract(request.post(path));
    }

    // ---------- GET ----------

    public Response get(String path) {
        return extract(baseRequest().get(path));
    }

    public Response getWithAuth(String path, String token) {
        return extract(baseRequestWithAuth(token).get(path));
    }

    public Response getWithQueryParam(String path, String query) {
        return extract(
                baseRequest()
                        .queryParam("q", query)
                        .get(path)
        );
    }

    public Response getWithPathParamAndAuth(String path, String id, String token) {
        return extract(
                baseRequestWithAuth(token)
                        .pathParam("id", id)
                        .get(path)
        );
    }

    // ---------- DELETE ----------

    public Response delete(String pathTemplate, String token, int basketItemId) {
        return extract(
                baseRequestWithAuth(token)
                        .pathParam("basketItemId", basketItemId)
                        .delete(pathTemplate)
        );
    }
}
