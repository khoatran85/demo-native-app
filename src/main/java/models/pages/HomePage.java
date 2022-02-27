package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.CommonMethods;

public class HomePage extends CommonMethods {
//    private final String appPurposeElem = "//*[@text='Demo app for the appium-boilerplate']";
    private final By appPurposeElem = MobileBy.xpath("//*[@text='Demo app for the appium-boilerplate']");
//    private final String textSupportLoc = "//*[@text='Support']";
    private final By textSupportLoc = MobileBy.xpath("//*[@text='Support']");
//    private final String androidIconSel = "//*[@text='Support']/preceding-sibling::android.widget.TextView[4]";
//    private final String iosIconSel = "//*[@text='Support']/preceding-sibling::android.widget.TextView[3]";
    private final By androidIconSel = MobileBy.xpath("//*[@text='Demo app for the appium-boilerplate']/following-sibling::android.widget.TextView[2]");
    private final By iosIconSel = MobileBy.xpath("//*[@text='Demo app for the appium-boilerplate']/following-sibling::android.widget.TextView[1]");

    private final AppiumDriver<MobileElement> driver;

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
    }

    public void verifyAppPurposeDisplayed(){
        Assert.assertTrue(isElementDisplayed(appPurposeElem));
    }

//    public HomePage verifyTextSupportDisplayed(){
//scrollToElement(textSupportLoc);
//        Assert.assertTrue(isElementDisplayed(textSupportLoc));
//        return this;
//    }

    public HomePage verifyAndroidIconDisplayed(){
        Assert.assertTrue(isElementDisplayed(androidIconSel));
        return this;
    }
    public void verifyIosIconDisplayed(){
        Assert.assertTrue(isElementDisplayed(iosIconSel));
    }

    public BottomNavigationComponent bottomNavigationComponent(){
        return new BottomNavigationComponent(driver);
    }
}
