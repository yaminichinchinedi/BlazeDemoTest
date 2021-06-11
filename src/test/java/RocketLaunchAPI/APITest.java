package RocketLaunchAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APITest {
    Response response = null;
    ResponseBody respBody = null;

    @BeforeTest
    public void invokeSuccessResponse() {
        try {
            //specific base uir
            RestAssured.baseURI = "https://api.spacexdata.com/v4";
            //request specification object
            RequestSpecification requestObject = RestAssured.given();
            //response object
            response = requestObject.request(Method.GET, "/launches/latest");
            respBody = response.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Verify the Response Header Status code 200 which is Success
    @Test(priority = 1)
    public void verifyResponseHeaderLatestLaunch() {
        try {
            int statuscode = response.getStatusCode();
            String statusline = response.statusLine();
            Assert.assertEquals(statuscode, 200, "Correct status code is returned which is success");
            Assert.assertTrue(statusline.contains("OK"), "Correct Status line returned");
            System.out.println("Response Status is: " + statuscode + " " + statusline);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Verify the Response Body contains id information, when successfully response is returned
    @Test(priority = 2)
    public void verifyIdLatestLaunch() {
        try {
            String id;
            JsonPath json = response.jsonPath();
            id = json.get("id").toString();
            if (!id.equals("") && !id.isEmpty()) {
                Assert.assertTrue(true, id);
                System.out.println("Latest Launch Id is: " + id);
            } else
                Assert.assertTrue(true, "Empty Id returned");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 3)
    public void verifyLatestLaunchPadDetails() {
        try {
            String launchpadid;
            String launchpaddetails;
            JsonPath json = response.jsonPath();
            launchpadid = json.get("launchpad").toString();
            launchpaddetails = json.get("details").toString();
            if (!launchpadid.equals("") && !launchpadid.isEmpty()) {
                Assert.assertTrue(true, launchpadid);
                System.out.println("LaunchPad Id is: " + launchpadid);
            } else
                Assert.assertTrue(true, "Empty Launch Pad Id returned");

            if (!launchpaddetails.equals("") && !launchpaddetails.isEmpty()) {
                Assert.assertTrue(true, launchpaddetails);
                System.out.println("LaunchPad Details are: " + launchpaddetails);
            } else {
                Assert.assertTrue(true, "Empty Launch Pad Details returned");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Prints entire Json Response Body
    @Test(priority = 4)
    public void getResponseTime() {
        try {
            long time = response.getTime();
            System.out.println("The Response time is: " + time);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Prints entire Json Response Body
    @Test(priority = 5)
    public void printLatestLaunches() {
        try {
            String body;
            if (response != null) {
                body = respBody.asString();
                if (body != null && !body.equals("") && !body.isEmpty())
                    System.out.println("The Response Body is: " + body);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterClass
    public void failureScenarioInvalidMethod() {
        try {
            //specific base uir
            RestAssured.baseURI = "https://api.spacexdata.com/v4";

            //request specification object
            RequestSpecification requestObject = RestAssured.given();

            //response object
            response = requestObject.request(Method.POST, "/launches/latest");

            int responseStatuscode = response.getStatusCode();
            String responsStatusLine = response.getStatusLine();
            boolean statusline = responsStatusLine.contains("Not Found");
            Assert.assertEquals(responseStatuscode, 404, "Invalid Method Type called");
            Assert.assertTrue(statusline, "Invalid Method called hence returned Not Found Response in status line");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
