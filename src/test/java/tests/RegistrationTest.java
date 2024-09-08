package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import specs.Specification;

import java.time.Clock;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static specs.LoginSpec.loginRequestSpec;
import static specs.LoginSpec.loginResponseSpec;

public class RegistrationTest {
    private final static String URL = "https://reqres.in/";
    private final static String TOKEN = "QpwL5tke4Pnpja7X4";

    @Test
    void loginUser() {
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
                .post(URL + "api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
        assertThat(loginResponseModel.getToken()).isEqualTo(TOKEN);
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
                .post(URL + "api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);
    }

    @Test
    void checkAvatar() {
        List<UserData> users =
                given()
                        .when()
                        .contentType(JSON)
                        .get(URL + "api/users?page=2")
                        .then()
                        .log().all()
                        .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).toList();
        List<String> ids = users.stream().map(x -> x.getId().toString()).toList();

        for (int i = 0; i < avatars.size(); i++) {
            Assertions.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }

    @Test
    void checkAvatarWithSpec() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseOk());
        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
    }

    @Test
    void registrationSuccess() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseOk());
        Integer id = 4;
        Register user = new Register("eve.holt@reqres.in", "pistol");

        SuccessRegistration successReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessRegistration.class);
        Assertions.assertNotNull(successReg.getId());

        Assertions.assertEquals(id, successReg.getId());
        Assertions.assertEquals(TOKEN, successReg.getToken());
    }

    @Test
    void unSuccessRegTest() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpecError());
        Register user = new Register("sydney@fife", "");

        UnSuccessRegistration unSuccessReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessRegistration.class);
        Assertions.assertEquals("Missing password", unSuccessReg.getError());

    }

    @Test
    void registrationWithCustomAllureAndStep() {
        LoginBodyModel bodyModel = new LoginBodyModel();

        bodyModel.setEmail("eve.holt@reqres.in");
        bodyModel.setPassword("cityslicka");

        LoginResponseModel loginResponseModel =
                step("Get authorization data", () ->
                        given(loginRequestSpec))
                        .body(bodyModel)
                        .when()
                        .post("login")
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class);
        step("Verify authorization response", () ->

                assertThat(loginResponseModel.getToken()).isEqualTo(TOKEN));
    }

    @Test
    void sortedYearsResource() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseOk());
        List<ColorsData> colorsData = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);
        List<Integer> years = colorsData.stream().map(ColorsData::getYear).toList();
        List<Integer> sortedYears = years.stream().sorted().toList();
        Assertions.assertEquals(sortedYears, years);
    }

    @Test
    void deleteUser() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpecUnique(204));
        given()
                .when()
                .delete("api/users/2")
                .then().log().all();
    }

    @Test
    void updateUser() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseOk());
        UserTime user = new UserTime();
        user.setName("morpheus");
        user.setJob("zion resident");

        UserTimeResponse response = given()
                .body(user)
                .when()
                .put("api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponse.class);

        String regex = "(.{5})$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "");

        Assertions.assertEquals(currentTime, response.getUpdatedAt().replaceAll(regex, ""));
    }
}