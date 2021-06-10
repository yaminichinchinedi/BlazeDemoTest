package RocketLaunchAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class APITest {
    Response response = null;
    ResponseBody respBody = null;

    @BeforeTest
    public void invokeSuccessResponse() {
        //specific base uir
        RestAssured.baseURI = "https://api.spacexdata.com/v4";

        //request specification object
        RequestSpecification requestObject = RestAssured.given();

        //response object
        response = requestObject.request(Method.GET, "/launches/latest");
        ResponseBody respBody = response.getBody();
    }


    //Verify the Response Header Status code 200 which is Success
    @Test(priority = 1)
    public void verifyResponseHeaderLatestLaunch() {
        int statuscode = response.getStatusCode();
        String statusline = response.statusLine().toString();
        Assert.assertEquals(statuscode, 200, "Correct status code is returned which is success");
        Assert.assertTrue(statusline.contains("OK"), "Correct Status line returned");
        System.out.println("Response Status is: " + statuscode + " " + statusline);
    }

    //Verify the Response Body contains id information, when successfully response is returned
    @Test(priority = 2)
    public void verifyIdLatestLaunch() {
        String id = "";
        JsonPath json = response.jsonPath();
        id = json.get("id").toString();
        if (id != "" && id != null && !id.isEmpty()) {
            Assert.assertTrue(true, id);
            System.out.println("Latest Launch Id is: " + id);
        } else
            Assert.assertTrue(id.isEmpty(), "Empty Id returned");
    }

    @Test(priority = 3)
    public void verifyLatestLaunchPadDetails() {
        String launchpadid = "";
        String launchpaddetails = "";
        JsonPath json = response.jsonPath();
        launchpadid = json.get("launchpad").toString();
        launchpaddetails = json.get("details").toString();
        if (launchpadid != "" && launchpadid != null && !launchpadid.isEmpty()) {
            Assert.assertTrue(true, launchpadid);
            System.out.println("LaunchPad Id is: " + launchpadid);
        } else
            Assert.assertTrue(launchpadid.isEmpty(), "Empty Launch Pad Id returned");

        if (launchpaddetails != "" && launchpaddetails != null && !launchpaddetails.isEmpty()) {
            Assert.assertTrue(true, launchpaddetails);
            System.out.println("LaunchPad Details are: " + launchpaddetails);
        } else
            Assert.assertTrue(launchpaddetails.isEmpty(), "Empty Launch Pad Details returned");
    }

    //Prints entire Json Response Body
    @Test(priority = 4)
    public void getResponseTime() {

        long time = response.getTime();
        System.out.println("The Response time is: " + time);
    }

    //Prints entire Json Response Body
    @Test(priority = 5)
    public void printLatestLaunches() {
        String body = "";
        List<JsonPath> jsonpathList;

        if (response != null) {
            respBody = response.getBody();
            body = respBody.asString();
            if (body != null && body != "" && !body.isEmpty())
                System.out.println("The Response Body is: " + body);

        }
    }


    @AfterTest
    public void failureScenarioInvalidMethod() {
        //specific base uir
        RestAssured.baseURI = "https://api.spacexdata.com/v4";

        //request specification object
        RequestSpecification requestObject = RestAssured.given();

        //response object
        response = requestObject.request(Method.POST, "/launches/latest");

        int responseStatuscode = response.getStatusCode();
        String responsStatusLine = response.getStatusLine().toString();
        boolean statusline = responsStatusLine.contains("Not Found");
        Assert.assertEquals(responseStatuscode, 404, "Invalid Method Type called");
        Assert.assertTrue(statusline, "Invalid Method called hence returned Not Found Response in status line");
    }
}
