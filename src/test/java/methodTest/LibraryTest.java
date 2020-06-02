package methodTest;

import RestApi.JsonRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LibraryTest {
    JsonRepository jsonRepository = new JsonRepository();

    @Test(dataProvider = "dataProv")
    public void addBook(String name , String name2) throws JSONException {
        RestAssured.baseURI = "http://216.10.245.166";
       String jsonObject = jsonRepository.autoGeneBookAddString(name,name2);
        System.out.println(jsonObject);
       Response response =  given().body(jsonObject).when().
                post("/Library/Addbook.php").then().
                assertThat().statusCode(200).contentType(ContentType.JSON).and().
                body("Msg" , equalTo("successfully added")).extract().response();
        JsonPath jsonPath = response.jsonPath();
        String res = jsonPath.get("Msg");

        System.out.println("\n" + res);

    }
    @DataProvider(name = "dataProv")
    public Object[][] dataProvider(){
        Object[][] objects = {{"ska", "cka"} ,{"sd" ,"sdfsdf"}};
        return objects;
    }
}
