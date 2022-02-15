package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.CommonMethods;

public class HomePage extends CommonMethods {
    private final String appPurposeElem = "//*[@text='Demo app for the appium-boilerplate']";
    private final String textSupportLoc = "//*[@text='Support']";
    private final By androidIconSel = MobileBy.xpath("//*[@text='Support']/preceding-sibling::android.widget.TextView[4]");
    private final By iosIconSel = MobileBy.xpath("//*[@text='Support']/preceding-sibling::android.widget.TextView[3]");

    private final AppiumDriver<MobileElement> driver;

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
    }

    public void verifyAppPurposeDisplayed(){
        Assert.assertTrue(isElementDisplayed(appPurposeElem));
    }
    public void verifyTextSupportDisplayed(){
        Assert.assertTrue(isElementDisplayed(textSupportLoc));
    }



}
