package tests;

import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.Test;
import reqres_objects.JobUser;
import reqres_objects.ResourceList;
import reqres_objects.UsersList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReqResTest {

    @Test (description = "Get list users, verify 'total' and 'last name' field first user")
    public void getListUsersTest() {
        String body =
                given()
                        .log().all()
                .when()
                        .get("https://reqres.in/api/users?page=2")
                .then()
                        .statusCode(200)
                        .extract().body().asString();
        System.out.println("*** OBJECT without @Expose annotation ***");
        UsersList usersList = new Gson().fromJson(body, UsersList.class);
        System.out.println(usersList);
        Assert.assertEquals(usersList.getTotal(), 12);
        Assert.assertEquals(usersList.getData().get(0).getLastName(), "Lawson");
    }

    @Test (description = "Get single user, verify 'first name' and 'last name' field")
    public void getSingleUserTest() {
        given()
                .log().all()
        .when()
                .get("https://reqres.in/api/users/2")
        .then()
                .log().all()
                .statusCode(200)
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"));
    }

    @Test (description = "Get a non-existent user")
    public void getSingleUserNotFoundTest() {
        given()
                .log().all()
        .when()
                .get("https://reqres.in/api/users/23")
        .then()
                .log().all()
                .statusCode(404);
    }

    @Test (description = "Get the list resource and check it with the file")
    public void getListResourceTest() throws FileNotFoundException {
        ResourceList expectedList = new Gson().fromJson(new FileReader("src/test/java/resources/expectedList.json"), ResourceList.class);
        String response =
            given()
                    .log().all()
            .when()
                    .get("https://reqres.in/api/unknown")
            .then()
                    .log().all()
                    .statusCode(200)
                    .extract().body().asString();
        ResourceList resourceList = new Gson().fromJson(response, ResourceList.class);
        Assert.assertEquals(resourceList.getData(), expectedList.getData());
    }

    @Test  (description = "Get single resource, verify 'name' and 'year' field")
    public void getSingleResourceTest() {
        given()
                .log().all()
        .when()
                .get("https://reqres.in/api/unknown/2")
        .then()
                .log().all()
                .statusCode(200)
                .body("data.name", equalTo("fuchsia rose"))
                .body("data.year", equalTo(2001));
    }

    @Test (description = "Get a non-existent resource")
    public void getSingleResourceNotFoundTest() {
        given()
                .log().all()
        .when()
                .get("https://reqres.in/api/unknown/23")
        .then()
                .log().all()
                .statusCode(404);
    }

    @Test (description = "Create user and verify data")
    public void postCreateUserTest() {
        JobUser user = JobUser.builder()
                .name("morpheus")
                .job("leader")
                .build();
        given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(user)
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .log().all()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"));
    }

    @Test (description = "Update user")
    public void putUpdateUserTest() {
        JobUser user = JobUser.builder()
                .name("morpheus")
                .job("leader")
                .build();
        String body =
                given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .body(user)
                .when()
                    .post("https://reqres.in/api/users")
               .then()
                    .log().all()
                    .statusCode(201)
                    .extract().body().asString();
        JobUser jobUser = new Gson().fromJson(body, JobUser.class);
        user = JobUser.builder()
                .name(jobUser.getName())
                .job("zion resident")
                .build();
        given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(user)
        .when()
                .put("https://reqres.in/api/users/2")
        .then()
                .log().all()
                .statusCode(200)
                .body("job", equalTo("zion resident"));
    }

    @Test (description = "Delete user")
    public void deleteUserTest() {
        given()
                .log().all()
        .when()
                .delete("https://reqres.in/api/users/2")
        .then()
                .log().all()
                .statusCode(204);
    }
}
