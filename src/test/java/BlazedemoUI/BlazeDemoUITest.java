package BlazedemoUI;

import UIPageObjects.BlazeDemoPageObjects;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BlazeDemoUITest {
    public WebDriver driver;
    public WebDriverWait wait;
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

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
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title, "BlazeDemo", "BlazeDemo title was verified");
        String welcomeText = driver.findElement(By.xpath(BlazeDemoPageObjects.BlazedemoWelcomeTitle)).getText();
        Assert.assertEquals(welcomeText, "Welcome to the Simple Travel Agency!", "Welcome Title Printed successfully");
    }

    @Test(priority = 1)
    public void submitFlightSearchDetails() throws InterruptedException {
        extentTest = extentReports.createTest("submitFlightSearchDetails");
        WebElement WE_DepartureCity;
        WebElement WE_DestinationCity;
        WebElement WB_SubmitFlightSearch = driver.findElement(By.xpath(BlazeDemoPageObjects.submitFlightSearchButton));
        wait.until(ExpectedConditions.visibilityOf(WB_SubmitFlightSearch));
        WE_DepartureCity = driver.findElement(By.xpath(BlazeDemoPageObjects.departurePort));
        Select departureCity = new Select(WE_DepartureCity);
        departureCity.selectByIndex(3);
        WebElement selectedDeparture = departureCity.getFirstSelectedOption();
        String selectedDepCity = selectedDeparture.getText();
        WE_DestinationCity = driver.findElement(By.xpath(BlazeDemoPageObjects.destinationPort));
        Select destinationCity = new Select(WE_DestinationCity);
        destinationCity.selectByIndex(2);
        WebElement selectedDestination = destinationCity.getFirstSelectedOption();
        String selectedDesCity = selectedDestination.getText();
        WB_SubmitFlightSearch.click();
        Thread.sleep(2000);
        String title = driver.getTitle();
        Assert.assertEquals(title, "BlazeDemo - reserve", "BlazeDemo title was verified");
        String flightsListTitle = driver.findElement(By.xpath(BlazeDemoPageObjects.BlazedemoFlightListTitle)).getText();
        Assert.assertEquals(flightsListTitle, "Flights from " + selectedDepCity + " to " + selectedDesCity + ":", "Welcome Title Printed successfully");
    }

    @Test(priority = 2, dependsOnMethods = {"submitFlightSearchDetails"})
    public void chooseTheflightSuccessfully() throws InterruptedException {
        extentTest = extentReports.createTest("chooseTheflightSuccessfully");
        WebElement WB_chooseFlightButton = driver.findElement(By.xpath(BlazeDemoPageObjects.chooseFlightButton));
        wait.until(ExpectedConditions.visibilityOf(WB_chooseFlightButton));
        WB_chooseFlightButton.click();
        Thread.sleep(2000);
        String title = driver.getTitle();
        Assert.assertEquals(title, "BlazeDemo Purchase", "BlazeDemo Purchase title was verified");
        String purchaseFlightPageTitle = driver.findElement(By.xpath(BlazeDemoPageObjects.BlazedemoFlightPurchaseTitle)).getText();
        Assert.assertEquals(purchaseFlightPageTitle, "Your flight from TLV to SFO has been reserved.", "Title of the Purchase flight page displayed successfully");
    }

    @Test(priority = 3, dependsOnMethods = {"submitFlightSearchDetails", "chooseTheflightSuccessfully"})
    public void submitPurchaseDetailsVerifyConfirmationId() throws InterruptedException {
        extentTest = extentReports.createTest("submitPurchaseDetailsVerifyConfirmationId");
        boolean confirmationIdExists;
        WebElement purchaseFlightButton = driver.findElement(By.xpath(BlazeDemoPageObjects.purchaseFlightButton));
        wait.until(ExpectedConditions.visibilityOf(purchaseFlightButton));
        WebElement pname = driver.findElement(By.xpath(BlazeDemoPageObjects.Name));
        WebElement paddress = driver.findElement(By.xpath(BlazeDemoPageObjects.Address));
        WebElement pcity = driver.findElement(By.xpath(BlazeDemoPageObjects.City));
        WebElement pstate = driver.findElement(By.xpath(BlazeDemoPageObjects.State));
        WebElement pzipcode = driver.findElement(By.xpath(BlazeDemoPageObjects.zipCode));
        WebElement pcreditCardNumber = driver.findElement(By.xpath(BlazeDemoPageObjects.creditCardNumber));
        WebElement pnameOnCard = driver.findElement(By.xpath(BlazeDemoPageObjects.nameOnCard));
        pname.sendKeys("name");
        paddress.sendKeys("address");
        pcity.sendKeys("city");
        pstate.sendKeys("state");
        pzipcode.sendKeys("zipcode");
        pcreditCardNumber.sendKeys("98867867667");
        pnameOnCard.sendKeys("nameonCard");
        purchaseFlightButton.click();
        Thread.sleep(2000);
        String title = driver.getTitle();
        Assert.assertEquals(title, "BlazeDemo Confirmation", "BlazeDemo Purchase title was verified");
        confirmationIdExists = driver.findElement(By.xpath(BlazeDemoPageObjects.confirmationIdField)).isDisplayed();
        if (confirmationIdExists)
            confirmationIdExists = driver.findElement(By.xpath(BlazeDemoPageObjects.confirmationIdFieldValue)).isDisplayed();
        Assert.assertTrue(confirmationIdExists, "Confirmation Id got displayed");
    }
}

