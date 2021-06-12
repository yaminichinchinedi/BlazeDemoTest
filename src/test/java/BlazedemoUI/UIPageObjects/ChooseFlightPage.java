package BlazedemoUI.UIPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ChooseFlightPage {

    public By chooseFlightButton = By.xpath("(//div//table//tbody//tr[1]//td[1]//input[@class='btn btn-small'])");
    public By BlazedemoFlightPurchaseTitle = By.xpath("//body//div//h2");

    public void chooseFlightDetails(WebDriver driver, WebDriverWait wait) throws InterruptedException {
        WebElement WB_chooseFlightButton = driver.findElement(chooseFlightButton);
        wait.until(ExpectedConditions.visibilityOf(WB_chooseFlightButton));
        WB_chooseFlightButton.click();
        Thread.sleep(2000);
        String title = driver.getTitle();
        Assert.assertEquals(title, "BlazeDemo Purchase", "BlazeDemo Purchase title was verified");
        String purchaseFlightPageTitle = driver.findElement(BlazedemoFlightPurchaseTitle).getText();
        Assert.assertEquals(purchaseFlightPageTitle, "Your flight from TLV to SFO has been reserved.", "Title of the Purchase flight page displayed successfully");
    }
}
