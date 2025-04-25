import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PutRequest {
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";

    Response response = given()
            .header("Content-Type", "text/plain; charset=UTF-8")
            .header("Accept", "*/*")
            .body(requestBody)
            .when()
            .put("/put")
            .then()
            .extract().response();

//        System.out.println("Response Status Code: " + response.getStatusCode());
//        System.out.println("Response Body: " + response.asString());

        response.then()
            .statusCode(200)
            .body("headers.content-type", containsString("text/plain"))
            .body("headers.accept", equalTo("*/*"))
            .body("headers.host", equalTo("postman-echo.com"))
            .body("headers.x-forwarded-proto", equalTo("https"))
            .body("headers.x-forwarded-port", equalTo("443"))
            .body("json", nullValue()) // Поле json равно null
            .body("url", equalTo("https://postman-echo.com/put"));
    }
}
