import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGetRequestWoops() {
        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .header("Accept", "*/*")
                .header("Cache-Control", "no-cache")
                .header("User-Agent", "PostmanRuntime/7.6.1")
        .when()
                .get("/get")
        .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.cache-control", equalTo("no-cache"))
                .body("headers.user-agent", equalTo("PostmanRuntime/7.6.1"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }
    @Test
    public void testGetRequest() {
        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .header("Accept", "*/*")
                .header("Cache-Control", "no-cache")
                .header("User-Agent", "PostmanRuntime/7.29.0")
                .header("Accept-Encoding", "gzip, deflate, br")
        .when()
                .get("/get")
        .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.cache-control", equalTo("no-cache"))
                .body("headers.user-agent", equalTo("PostmanRuntime/7.29.0"))
                .body("headers.accept-encoding", equalTo("gzip, deflate, br"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }
}

