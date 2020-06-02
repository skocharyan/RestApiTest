package methodTest;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetMethodTest {
       @Test
    public void getMethodTest(){
        RestAssured.baseURI="https://maps.googleapis.com";


        given().
                param("location","40.151546,44.484188").
                param("radius","200").
                param("type" , "restaurant").
                param("key","AIzaSyCf5noLPkvmkEQIhIoEOaEs5U5q251KwgY").
                when().
                get("/maps/api/place/nearbysearch/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("results[0].name",equalTo("3rd masi ghars"));


    }

}
