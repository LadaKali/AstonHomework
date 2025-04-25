import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostRequests {
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }
    @Test
    public void testRawText () {

        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .header("Content-Type", "text/plain")
                .header("Accept", "*/*")
                .header("Cache-Control", "no-cache")
                .header("User-Agent", "PostmanRuntime/7.29.0")
                .header("Accept-Encoding", "gzip, deflate, br")
                .body(requestBody)
        .when()
                .post("/post")
        .then()
                .statusCode(200)
                .body("headers.content-type", containsString("text/plain"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.cache-control", equalTo("no-cache"))
                .body("headers.user-agent", equalTo("PostmanRuntime/7.29.0"))
                .body("headers.accept-encoding", equalTo("gzip, deflate, br"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    public void testFormText() {
        Response response = given()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept", "*/*")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .extract().response();

//        System.out.println("Response Status Code: " + response.getStatusCode());
//        System.out.println("Response Body: " + response.asString());

        response.then()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("headers.content-type", containsString("application/x-www-form-urlencoded"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("json.foo1", equalTo("bar1"))
                .body("json.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/post"));
    }
}
