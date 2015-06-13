package service;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testflow.DefaultRestTest;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

public class RestService {

    private final static Logger log = LoggerFactory.getLogger(RestService.class);

    public RestService() {
        RestAssured.baseURI = DefaultRestTest.WIREMOCK_URI;
    }

    public void getUserData(String userId) {
        Response response =
            given().
                contentType("application/json").
            when().
                get("/user/" + userId).
            then().
                body(containsString("id")).
                body(containsString("login")).
                body(containsString("www")).
                statusCode(200).
            extract().response();

        log.info("-- retrieved user data for user: {}", (String) response.path("login"));
    }

    public void addNewUser(String login, String www) {
        Response response =
            given().
                contentType("application/json").
            body("{\"login\":\"" + login + "\",\n" +
                    "\"www\":\"" + www + "\"}").
            when().
                post("/addNewUser").
            then().
                body(containsString("id")).
                body(containsString("login")).
                body(containsString("www")).
                statusCode(200).
            extract().response();

        log.info("-- new user created with login: {}", (String) response.path("login"));
    }

    public void postHealthcheckMapping() {
        given().
            body("{\"request\":{\"url\":\"/ping\",\"method\":\"GET\"}," +
                "\"response\":{\"status\":200,\"body\":\"pong!\\n\"}}").
        when().post("__admin/mappings/new");
    }

    public void getHealthcheckPing() {
        when().get("/ping").
        then().body(containsString("pong!"));
    }

}
