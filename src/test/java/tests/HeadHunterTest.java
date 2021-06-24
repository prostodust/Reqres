package tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import headhunter_objects.VacanciesList;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HeadHunterTest {

    @Test
    public void qaSearchTest() {
        String body =
                given()
                .when()
                        .get("https://api.hh.ru/vacancies?text=QA")
                .then()
//                .log().all()
                        .statusCode(200)
                        .extract().body().asString();

        System.out.println("*** BODY ***");
        System.out.println(body);

        //Without @Expose annotation
        System.out.println("*** OBJECT without @Expose annotation ***");
        VacanciesList vacanciesListWithoutExpose = new Gson().fromJson(body, VacanciesList.class);
        System.out.println(vacanciesListWithoutExpose);
        //With @Expose annotation
        System.out.println("*** OBJECT with @Expose annotation ***");
        VacanciesList vacanciesList = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                .fromJson(body, VacanciesList.class);
        System.out.println(vacanciesList);

        //Assert example:
        //Assert.assertEquals(detailsPage.getSalaryFromText(), vacanciesList.getItems().get(0).getSalary().getFrom());
    }
}