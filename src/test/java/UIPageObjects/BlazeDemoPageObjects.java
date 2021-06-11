package UIPageObjects;

public class BlazeDemoPageObjects {
    public static String submitFlightSearchButton = "//input[@type='submit'][contains(@class,'btn btn-primary')][contains(@value,'Find Flights')]";
    public static String chooseFlightButton = "(//div//table//tbody//tr[1]//td[1]//input[@class='btn btn-small'])";
    public static String purchaseFlightButton = "//input[@type ='submit'][@value='Purchase Flight']";
    public static String confirmationIdField = "(//div//table//tbody//tr[1]//td[1])";
    public static String confirmationIdFieldValue = "(//div//table//tbody//tr[1]//td[2])";
    public static String BlazedemoWelcomeTitle = "//h1[contains(string(),'Welcome to the Simple Travel Agency!')]";
    public static String BlazedemoFlightListTitle = "//body//div//h3";
    public static String BlazedemoFlightPurchaseTitle = "//body//div//h2";
    public static String destinationPort = "//select[@name='toPort']";
    public static String departurePort = "//select[@name='fromPort']";
    public static String Name = "//input[@id='inputName']";
    public static String Address = "//input[@id='address']";
    public static String City = "//input[@id='city']";
    public static String State = "//input[@id='state']";
    public static String zipCode = "//input[@id='zipCode']";
    public static String creditCardNumber = "//input[@id='creditCardNumber']";
    public static String nameOnCard = "//input[@id='nameOnCard']";
}
