package BlazedemoUI.UIPageObjects;

import BlazedemoUI.DataObjects.PurchaseData;
import BlazedemoUI.DataRetrieve.UIDataRetrieve;
import BlazedemoUI.DataRetrieveImpl.UIDataRetrieveImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class SubmitPurchaseDetailsPage {

    public By flightPurchaseButton = By.xpath("//input[@type ='submit'][@value='Purchase Flight']");
    public By confirmationIdField = By.xpath("(//div//table//tbody//tr[1]//td[1])");
    public By confirmationIdFieldValue = By.xpath("(//div//table//tbody//tr[1]//td[2])");
    public By Name = By.xpath("//input[@id='inputName']");
    public By Address = By.xpath("//input[@id='address']");
    public By City = By.xpath("//input[@id='city']");
    public By State = By.xpath("//input[@id='state']");
    public By zipCode = By.xpath("//input[@id='zipCode']");
    public By creditCardNumber = By.xpath("//input[@id='creditCardNumber']");
    public By nameOnCard = By.xpath("//input[@id='nameOnCard']");

    public void submitPurchaseDetailsData(WebDriver driver, WebDriverWait wait) throws InterruptedException, IOException {
        UIDataRetrieve uiDataRetrieve = new UIDataRetrieveImpl();
        boolean confirmationIdExists;
        PurchaseData purchaseData = uiDataRetrieve.getPurchaseData();
        WebElement purchaseFlightButton = driver.findElement(flightPurchaseButton);
        wait.until(ExpectedConditions.visibilityOf(purchaseFlightButton));
        WebElement pname = driver.findElement(Name);
        WebElement paddress = driver.findElement(Address);
        WebElement pcity = driver.findElement(City);
        WebElement pstate = driver.findElement(State);
        WebElement pzipcode = driver.findElement(zipCode);
        WebElement pcreditCardNumber = driver.findElement(creditCardNumber);
        WebElement pnameOnCard = driver.findElement(nameOnCard);
        pname.sendKeys(purchaseData.getName());
        paddress.sendKeys(purchaseData.getAddress());
        pcity.sendKeys(purchaseData.getCity());
        pstate.sendKeys(purchaseData.getState());
        pzipcode.sendKeys(purchaseData.getZipcode());
        pcreditCardNumber.sendKeys(purchaseData.getCardNumber());
        pnameOnCard.sendKeys(purchaseData.getNameonCard());
        purchaseFlightButton.click();
        Thread.sleep(2000);
        String title = driver.getTitle();
        Assert.assertEquals(title, "BlazeDemo Confirmation", "BlazeDemo Purchase title was verified");
        confirmationIdExists = driver.findElement(confirmationIdField).isDisplayed();
        if (confirmationIdExists)
            confirmationIdExists = driver.findElement(confirmationIdFieldValue).isDisplayed();
        Assert.assertTrue(confirmationIdExists, "Confirmation Id got displayed");
    }
}