package BlazedemoUI;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.PageFactoryFinder;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BlazeDemoUITest {
    public static WebDriver driver;
    public WebDriverWait wait;

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    @FindBy(how = How.XPATH, using = "//input[@type ='submit'][@value='Find Flights']")
    private WebElement WT_BlazeDemo_submitFlightSearch;

    @FindBy(how = How.XPATH, using = "(//div//table//tbody//tr[1]//td[1]//input[@class='btn btn-small'])")
    private WebElement WT_BlazeDemo_chooseFlight;

    @FindBy(how = How.XPATH, using = "//input[@type ='submit'][@value='Purchase Flight'")
    private WebElement WT_BlazeDemo_purchaseFlight;

    @FindBy(how = How.XPATH, using = "(//div//table//tbody//tr[1]//td[1]")
    private WebElement WT_BlazeDemo_confirmationId;

    @FindBy(how = How.ID, using = "(//div//table//tbody//tr[1]//td[2]")
    private WebElement WT_BlazeDemo_confirmationIDValue;

    @BeforeTest
    public void setExtent() throws Exception{
        try {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/BlazeDemoReport.html");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("System Test Report");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("HostName", "LocalHost");
        extentReports.setSystemInfo("Tester Name", "Yamini");
        extentReports.setSystemInfo("Browser Name", "Chrome Browser");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterTest
    public void endReport() {
        extentReports.flush();
    }

    @BeforeClass
    public void setUp() throws Exception{
        try {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers//chromedriver");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--test-type");
            options.addArguments("--disable-extensions");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--allow-running-insecure-content");
            options.addArguments("--disable-translate");
            options.addArguments("--always-authorize-plugins");
            options.addArguments("--disable-infobars");
            options.addArguments("--enable-automation");
            options.addArguments("--no-sandbox");
            options.addArguments("--no-default-browser-check");
            options.addArguments("--no-first-run'");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");

            //options.setExperimentalOption("useAutomationExtension", false);
            driver = new ChromeDriver(options);
            //driver.manage().window().maximize();
            driver.get("http://blazedemo.com");
            //System.out.println("opened url");
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @AfterClass
    public void closeBrowser() {
       driver.quit();
    }

    @Test(priority =0)
    public void verifyBlazeDemoTitle() throws Exception{
        try {
        extentTest = extentReports.createTest("verifyBlazeDemoTitle");
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title, "BlazeDemo", "BlazeDemo title was verified");
         } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test(priority=1)
  public void submitFlightSearchDetails() throws Exception {
      try {
        extentTest = extentReports.createTest("submitFlightSearchDetails");
        Thread.sleep(2000);
        WT_BlazeDemo_submitFlightSearch.click();
        WT_BlazeDemo_submitFlightSearch.submit();
        Thread.sleep(2000);
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title, "BlazeDemo - reserve", "BlazeDemo Reserve title was verified");
         } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority=2, dependsOnMethods = {"submitFlightSearchDetails"})
    public void chooseTheflightSuccessfully() throws Exception {
        try {
        extentTest = extentReports.createTest("chooseTheflightSuccessfully");
        WT_BlazeDemo_chooseFlight.submit();
        Thread.sleep(2000);
        String title = driver.getTitle();
        Assert.assertEquals(title, "BlazeDemo Purchase", "BlazeDemo Purchase title was verified");
         } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority=3, dependsOnMethods = {"submitFlightSearchDetails", "chooseTheflightSuccessfully"})
    public void submitPurchaseDetailsVerifyConfirmationId() throws Exception {
        try {
        extentTest = extentReports.createTest("submitPurchaseDetailsVerifyConfirmationId");
        WT_BlazeDemo_purchaseFlight.submit();
        Thread.sleep(2000);
        String title = driver.getTitle();
        Assert.assertEquals(title, "BlazeDemo Purchase", "BlazeDemo Purchase title was verified");
        Assert.assertTrue(WT_BlazeDemo_confirmationId.isDisplayed(),"Confirmation Id is displayed");
        Assert.assertTrue(WT_BlazeDemo_confirmationIDValue.isDisplayed(),"Confirmation Id value is displayed");
             } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}



