package methodTest;

import com.beust.jcommander.Parameter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostMethodTest {
    private String place_ID = null;
    private String body = "{" +
            "\"location\":{  \"lat\":\"40.125170\", \"lng\":\"44.482879\" }," +
            " \"accuracy\":50," +
            "\"name\":\"Frontline house\"," +
            "\"phone_number\":\"(+91) 983 893 3937\"," +
            "\"address\" :\"29, side layout, cohen 09\"," +
            "\"types\": [\"shoe park\", \"shop\"]," +
            "\"website\" :\"http://google.com\"," +
            "\"language\" :\"French-IN\"," +
            " \"name\" :\"Some Place\"" +
            " }";
//    @Test
//    public void postMethod() {
//        RestAssured.baseURI = "http://216.10.245.166";
//        given().
//                queryParam("key", "qaclick123").
//                body(body).and().
//                when().
//                get("/maps/api/place/add/json").
//                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
//                body("status",equalTo("OK"));
//
//
//    }
    @Test
    @Parameters("paramName")
    public void responseToString(String paramName ) {
        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given().
                queryParam("key", "qaclick123").
                body(body).and().
                when().
                post("/maps/api/place/add/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK")).extract().response();
        JsonPath jsonPath = response.jsonPath();
        String place_id = jsonPath.get("place_id");
        place_ID = place_id;
        System.out.println(paramName + "ssssssssssssssssssssssssssssssssssssssss");
    }

    @Test(dependsOnMethods = "responseToString")
    public void deleteMethod() {
        RestAssured.baseURI = "http://216.10.245.166";
        given().queryParam("key", "qaclick123").
                body("{ " +
                        " \"place_id\" : \"" + place_ID + "\""
                        + "}").and().
                when().post("/maps/api/place/delete/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK"));

    }
}
