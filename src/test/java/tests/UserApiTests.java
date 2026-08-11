package tests; // Перевір, щоб назва пакету збігалася з твоєю

import dto.RspCreateUserDTO;
import dto.UserBuilder;
import dto.UserDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UserApiTests {

    UserDTO USER;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Беремо готового юзера з нашого нового класу
        USER = UserBuilder.createDefaultUser();
    }

    @Test(priority = 1)
    public void createUserTest() throws InterruptedException {

        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(USER)
                .when()
                .post("/user")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        RspCreateUserDTO responseDTO = response.as(RspCreateUserDTO.class);

        assertEquals(responseDTO.getCode(), 200);
        assertEquals(responseDTO.getType(), "unknown");
        assertNotNull(responseDTO.getMessage());

        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void loginUserTest() {

        Response response = given()
                .log().all()
                .queryParam("username", USER.getUsername())
                .queryParam("password", USER.getPassword())
                .when()
                .get("/user/login")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        RspCreateUserDTO responseDTO = response.as(RspCreateUserDTO.class);

        assertEquals(responseDTO.getCode(), 200);
        assertNotNull(responseDTO.getMessage());
    }

    @Test(priority = 3)
    public void getUserTest() {

        Response response = given()
                .log().all()
                .pathParam("username", USER.getUsername())
                .when()
                .get("/user/{username}")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        UserDTO responseUser = response.as(UserDTO.class);

        assertEquals(responseUser.getUsername(), USER.getUsername());
        assertEquals(responseUser.getFirstName(), USER.getFirstName());
        assertEquals(responseUser.getEmail(), USER.getEmail());
    }

    @Test(priority = 4)
    public void updateUserTest() throws InterruptedException { // Додали throws
        USER.setFirstName("VladUpdated");
        USER.setPhone("987654321");

        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("username", USER.getUsername())
                .body(USER)
                .when()
                .put("/user/{username}")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        RspCreateUserDTO responseDTO = response.as(RspCreateUserDTO.class);
        assertEquals(responseDTO.getCode(), 200);

        Thread.sleep(8000);
    }

    @Test(priority = 5)
    public void getUserAfterUpdateTest() {

        Response response = given()
                .log().all()
                .pathParam("username", USER.getUsername())
                .when()
                .get("/user/{username}")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        UserDTO responseUser = response.as(UserDTO.class);


        assertEquals(responseUser.getFirstName(), "VladUpdated");
        assertEquals(responseUser.getPhone(), "987654321");
    }

    @Test(priority = 6)
    public void deleteUserTest() throws InterruptedException { // Додали throws
        Response response = given()
                .log().all()
                .pathParam("username", USER.getUsername())
                .when()
                .delete("/user/{username}")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        RspCreateUserDTO responseDTO = response.as(RspCreateUserDTO.class);
        assertEquals(responseDTO.getCode(), 200);

        Thread.sleep(8000);
    }

    @Test(priority = 7)
    public void getUserAfterDeleteTest() {

        given()
                .log().all()
                .pathParam("username", USER.getUsername())
                .when()
                .get("/user/{username}")
                .then()
                .log().all()
                .statusCode(404); // Зверни увагу, тут статус 404 Not Found
    }

    @Test(priority = 8)
    public void logoutUserTest() {

        Response response = given()
                .log().all()
                .when()
                .get("/user/logout")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        RspCreateUserDTO responseDTO = response.as(RspCreateUserDTO.class);
        assertEquals(responseDTO.getCode(), 200);
    }
}