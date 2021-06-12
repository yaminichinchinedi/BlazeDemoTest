package BlazedemoUI.UIPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BlazeDemoHomePage {
    public By submitFlightSearchButton = By.xpath("//input[@type='submit'][contains(@class,'btn btn-primary')][contains(@value,'Find Flights')]");
    public By BlazedemoFlightListTitle = By.xpath("//body//div//h3");
    public By destinationPort = By.xpath("//select[@name='toPort']");
    public By departurePort = By.xpath("//select[@name='fromPort']");
    public By BlazedemoWelcomeTitle = By.xpath("//h1[contains(string(),'Welcome to the Simple Travel Agency!')]");

    public void verifyBlazeDemoTitle(WebDriver driver) {
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title, "BlazeDemo", "BlazeDemo title was verified");
        String welcomeText = driver.findElement(BlazedemoWelcomeTitle).getText();
        Assert.assertEquals(welcomeText, "Welcome to the Simple Travel Agency!", "Welcome Title Printed successfully");
    }

    public void  submitFligthSearchdetails(WebDriver driver, WebDriverWait wait) throws InterruptedException{
        WebElement WE_DepartureCity;
        WebElement WE_DestinationCity;
        WebElement WB_SubmitFlightSearch = driver.findElement(submitFlightSearchButton);
        wait.until(ExpectedConditions.visibilityOf(WB_SubmitFlightSearch));
        WE_DepartureCity = driver.findElement(departurePort);
        Select departureCity = new Select(WE_DepartureCity);
        departureCity.selectByIndex(3);
        WebElement selectedDeparture = departureCity.getFirstSelectedOption();
        String selectedDepCity = selectedDeparture.getText();
        WE_DestinationCity = driver.findElement(destinationPort);
        Select destinationCity = new Select(WE_DestinationCity);
        destinationCity.selectByIndex(2);
        WebElement selectedDestination = destinationCity.getFirstSelectedOption();
        String selectedDesCity = selectedDestination.getText();
        WB_SubmitFlightSearch.click();
        Thread.sleep(2000);
        String title = driver.getTitle();
        Assert.assertEquals(title, "BlazeDemo - reserve", "BlazeDemo title was verified");
        String flightsListTitle = driver.findElement(BlazedemoFlightListTitle).getText();
        Assert.assertEquals(flightsListTitle, "Flights from " + selectedDepCity + " to " + selectedDesCity + ":", "Welcome Title Printed successfully");
    }
}
