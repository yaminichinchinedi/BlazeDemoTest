package BlazedemoUI.DataRetrieveImpl;

import BlazedemoUI.DataObjects.PurchaseData;
import BlazedemoUI.DataRetrieve.UIDataRetrieve;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UIDataRetrieveImpl implements UIDataRetrieve {
    public PurchaseData purchase;
    String testDataPath;

    @Override
    public BlazedemoUI.DataObjects.PurchaseData getPurchaseData() throws IOException {
        purchase = new PurchaseData();
        testDataPath = System.getProperty("user.dir") + "//testdata.properties";
        Properties prop = new Properties();
        prop.load(new FileInputStream(testDataPath));
        String name = prop.getProperty("name");
        String address = prop.getProperty("address");
        String city = prop.getProperty("city");
        String state = prop.getProperty("state");
        String zipcode = prop.getProperty("zipcode");
        String cardnumber = prop.getProperty("cardnumber");
        String nameoncard = prop.getProperty("nameoncard");

        if (name != null && !name.equals(""))
            purchase.setName(name.trim());
        if (address != null && !address.equals(""))
            purchase.setAddress(address.trim());
        if (city != null && !city.equals(""))
            purchase.setCity(city.trim());
        if (state != null && !state.equals(""))
            purchase.setState(state.trim());
        if (zipcode != null && !zipcode.equals(""))
            purchase.setZipcode(zipcode.trim());
        if (cardnumber != null && !cardnumber.equals(""))
            purchase.setCardNumber(cardnumber.trim());
        if (nameoncard != null && !nameoncard.equals(""))
            purchase.setNameonCard(nameoncard.trim());
        return purchase;
    }
}
