package demoWebShop;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class addProductInBasketFromDemoWebShop {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demowebshop.tricentis.com/";
    }

    private String cookieValue = "1adc657c-c23c-4b28-b9fa-04f59c697435";
    private String messageValue = "The product has been added to your <a href=\"/cart\">shopping cart</a>";
    private String messageValueFollowers = "The product has been added to your <a href=\"/cart\">shopping cart</a>";
    private String bodyValue = "product_attribu" +
            "te_74_5_26" +
            "=81&product_attribute_74_6_27=83&product_attribute_74_3_28" +
            "=86&addtocart_74.EnteredQuantity=1";

    private String bodyValueFollowers = "giftcard_4.RecipientName=" +
            "Recipt&giftcard_4.SenderName=Name&giftcard_4.Message=" +
            "For+you!&addtocart_4.EnteredQuantity=3";

    @Test
    void addToBasketTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer", cookieValue)
                .body(bodyValue)
                .when()
                .post("/addproducttocart/details/74/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is(messageValue));
    }

    @Test
    void addAnonymousToBasketTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(bodyValue)
                .when()
                .post("/addproducttocart/details/74/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is(messageValue))
                .body("updatetopcartsectionhtml", is("(1)"));
    }

    @Test
    void addToFollowers() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer", "1adc657c-c23c-4b28-b9fa-04f59c697435")
                .body(bodyValueFollowers)
                .when()
                .post("https://demowebshop.tricentis.com/addproducttocart/details/4/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is(messageValueFollowers));
    }

    @Test
    void addToFollowersForAnonymous () {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(bodyValueFollowers)
                .when()
                .post("/addproducttocart/details/4/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is(messageValueFollowers));
    }
}