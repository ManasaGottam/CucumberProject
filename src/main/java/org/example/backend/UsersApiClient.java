package org.example.backend;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class UsersApiClient {
    private final RequestSpecification requestSpec = RestAssured.given();
    private static final Logger logger = LoggerFactory.getLogger(UsersApiClient.class);

    UsersApiClient() {
        requestSpec.baseUri("https://jsonplaceholder.typicode.com/posts/");
    }

    public List<User> getAllUsers() {
        ValidatableResponse response = requestSpec
                .given()
                .when()
                .log().all()
                .get()
                .then()
                .log().all()
                .statusCode(200);
        logger.info(format("getAllUsers() call is successful with statusCode: %s", response.extract().statusCode()));
        List<User> users = response.extract().jsonPath().getList("", User.class);
        return users;
    }

    public User getUserById(int id) {
        ValidatableResponse response = requestSpec
                .given()
                .get(format("%s", id))
                .then()
                .statusCode(200);
        logger.info(format("getUserById(%s) call is successful with statusCode: %s", id, response.extract().statusCode()));
        return response.extract().as(User.class);
    }

    public User createUser(User userToCreate) {
//        Map<String, String> values = new HashMap<>();
//        values.put("userId","111");
//        values.put("id","111");
//        values.put("title","ts title");
//        values.put("body","r body");
        ValidatableResponse response = requestSpec
                .given()
                .contentType(ContentType.JSON)
                .body(userToCreate)
                .header("Accept", "*/*")
                .post()
                .then()
                .statusCode(201);
        return response.extract().as(User.class);
    }
}
