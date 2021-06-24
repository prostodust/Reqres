package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class OnlinerTest {

    @Test
    public void getCurrencyUsdTest() {
        given()
        .when()
                .get("https://www.onliner.by/sdapi/kurs/api/bestrate?currency=USD&type=nbrb")
        .then()
                .statusCode(200);
    }

    @Test
    public void getCurrencyEurTest() {
        given()
        .when()
                .get("https://www.onliner.by/sdapi/kurs/api/bestrate?currency=EUR&type=nbrb")
        .then()
                .statusCode(200);
    }

    @Test
    public void getCurrencyRubTest() {
        given()
        .when()
                .get("https://www.onliner.by/sdapi/kurs/api/bestrate?currency=RUB&type=nbrb")
        .then()
                .statusCode(200);
    }

    @Test
    public void getCurrencyUsdAmountTest() {
        given()
                .log().all()
        .when()
                .get("https://www.onliner.by/sdapi/kurs/api/bestrate?currency=USD&type=nbrb")
        .then()
                .log().all()
                .body("amount", equalTo("2,5362"))
                .statusCode(200);
    }
}