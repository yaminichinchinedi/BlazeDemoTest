package BlazedemoUI.UITestClasses;

import BlazedemoUI.UIPageObjects.*;
import BlazedemoUI.UIPageObjects.SubmitPurchaseDetailsPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BlazeDemoUITest {
    public WebDriver driver;
    public WebDriverWait wait;
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    BlazeDemoHomePage blazeDemoPage;
    ChooseFlightPage chooseFlightPage;
    SubmitPurchaseDetailsPage submitPurchaseDetailsPage;

    BlazeDemoUITest() {
        blazeDemoPage = new BlazeDemoHomePage();
        chooseFlightPage = new ChooseFlightPage();
        submitPurchaseDetailsPage = new SubmitPurchaseDetailsPage();
    }

    @BeforeClass
    public void setUp() {

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
        driver = new ChromeDriver(options);
        driver.get("http://blazedemo.com");
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
    }

    @BeforeTest
    public void setExtent() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/BlazeDemoReport.html");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("System Test Report");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("HostName", "LocalHost");
        extentReports.setSystemInfo("Tester Name", "Yamini");
        extentReports.setSystemInfo("Browser Name", "Chrome Browser");
    }

    @AfterTest
    public void endReport() {
        extentReports.flush();
    }

    @AfterClass
    public void closeAllBrowsers() {
        driver.quit();
    }

    @Test()
    public void verifyBlazeDemoTitle() {
        extentTest = extentReports.createTest("verifyBlazeDemoTitle");
        blazeDemoPage.verifyBlazeDemoTitle(driver);
    }

    @Test(priority = 1)
    public void submitFlightSearchDetails() throws InterruptedException {
        extentTest = extentReports.createTest("submitFlightSearchDetails");
        blazeDemoPage.submitFligthSearchdetails(driver, wait);
    }

    @Test(priority = 2, dependsOnMethods = {"submitFlightSearchDetails"})
    public void chooseTheflightSuccessfully() throws InterruptedException {
        extentTest = extentReports.createTest("chooseTheflightSuccessfully");
        chooseFlightPage.chooseFlightDetails(driver, wait);
    }

    @Test(priority = 3, dependsOnMethods = {"submitFlightSearchDetails", "chooseTheflightSuccessfully"})
    public void submitPurchaseDetailsVerifyConfirmationId() throws InterruptedException, IOException {
        extentTest = extentReports.createTest("submitPurchaseDetailsVerifyConfirmationId");
        submitPurchaseDetailsPage.submitPurchaseDetailsData(driver, wait);
    }
}

