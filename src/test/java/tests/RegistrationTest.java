package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import model.CreateUserModel;
import model.LoginBodyModel;
import model.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static specs.LoginSpec.loginRequestSpec;
import static specs.LoginSpec.loginResponseSpec;

public class RegistrationTest {

    @Test
    void Registration() {

        LoginBodyModel bodyModel = new LoginBodyModel();
        bodyModel.setEmail("eve.holt@reqres.in");
        bodyModel.setPassword("cityslicka");


        LoginResponseModel loginResponseModel = given()
                .log().uri()
                .log().headers()
                .log().body()
                .filter(new AllureRestAssured())
                .contentType(JSON)
                .body(bodyModel)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
        assertThat(loginResponseModel.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void createUser() {
        CreateUserModel bodyModel = new CreateUserModel();

        bodyModel.setJob("leader");
        bodyModel.setName("morpheus");

        given().
                log().uri()
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);
    }

    @Test
    void RegistrationWithCustomAllureAndStep() {

        LoginBodyModel bodyModel = new LoginBodyModel();
        bodyModel.setEmail("eve.holt@reqres.in");
        bodyModel.setPassword("cityslicka");

        LoginResponseModel loginResponseModel =
                step("Get authorization data", () ->
                        given(loginRequestSpec))
                .body(bodyModel)
                .when()
                .post("/login")
                .then()
                .spec(loginResponseSpec)
                .extract().as(LoginResponseModel.class);
        step("Verify authorization response", () ->
        assertThat(loginResponseModel.getToken()).isEqualTo("QpwL5tke4Pnpja7X4"));
    }
}