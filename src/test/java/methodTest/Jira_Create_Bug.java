package methodTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class Jira_Create_Bug {
    private String HOST = null;
    private String resource = null;
    private String issue_id = null;
    private  Logger logger = LogManager.getLogger(Jira_Create_Bug.class);


    private String b =  "{\n" +
            "  \"body\":\"sssssssssssssssssadadasdadadad\"\n" +
            "}";

    @BeforeClass
    public void beforeClass() {
        HOST = "http://localhost:9191";
        resource = "/rest/api/2/issue";

    }

    @Test
    public void createBug() {
        RestAssured.baseURI = HOST;
        Response response = given().auth().preemptive().basic("skocharyan1993", "my-angel1993").
                header("Content-Type", "application/json").body("{\n" +
                "    \"fields\": {\n" +
                "       \"project\":\n" +
                "       {\n" +
                "          \"key\": \"SKA\"\n" +
                "       },\n" +
                "       \"summary\": \"yyyyyyyyyyyyyyyyyy.\",\n" +
                "       \"description\": \"xxxxxxxxxxxxxxxxxxxxxxxxxxxxx\",\n" +
                "       \"issuetype\": {\n" +
                "          \"name\": \"Bug\"\n" +
                "       }\n" +
                "   }\n" +
                "}")
                .when().post(resource).then().and()
                .extract().response();
        String id = response.jsonPath().get("key");
        if (!id.isEmpty()) {
            issue_id = id;
            System.out.println("bug created");
            logger.info("creating bug with " + issue_id + " id");
            assertTrue(true);

        } else {
            assertTrue(false);
        }


    }

    @Test(dependsOnMethods = "createBug")
    public void addCommentToIssue() {
        RestAssured.baseURI = HOST;
        Response r = given().auth().preemptive().basic("skocharyan1993", "my-angel1993")
                .header("Content-Type", "application/json").body(b).pathParam("issueIdOrKey", issue_id)
                .when().post("/rest/api/2/issue/{issueIdOrKey}/comment")
                .then().assertThat().statusCode(201).extract().response();
        logger.info("adding comment to " + issue_id + " issue");
    }


    @Test(dependsOnMethods = "addCommentToIssue")
    public void deleteIssue() {

        RestAssured.baseURI = HOST;
        Response r =
                given().auth().preemptive().basic("skocharyan1993", "my-angel1993").
                        header("Content-Type", "application/json").pathParam("issueIdOrKey", issue_id)
                        .when().delete("/rest/api/2/issue/{issueIdOrKey}")
                        .then().assertThat().statusCode(204).extract().response();
        System.out.println(r.asString());
        System.out.println("bug " + issue_id + "  deleted");
        logger.info("deleting " + issue_id + " bug");
    }
}
