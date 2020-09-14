package page.network;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ArpPage extends BasePage{

    private static final By ARP_SUBMIT_BUTTON = By.id("submitVlan8021q");


    public ArpPage(WebDriver driver) {
        super(driver);
    }


    public ArpPage clickArpStates(int[] indexes) {
        for(int i = 0; i < indexes.length; i++){
            clickElement( By.id("proxyArpState" + indexes[i]) );
        }
        return this;
    }

    public void clickSubmitButton() {
        clickElement(ARP_SUBMIT_BUTTON);
    }
}