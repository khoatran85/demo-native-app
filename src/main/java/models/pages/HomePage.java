package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonMethods;

import javax.xml.xpath.XPath;
import java.time.Duration;

public class HomePage extends CommonMethods {
//    private final By appPurposeSel = MobileBy.AccessibilityId("Demo app for the appium-boilerplate");
//    private final By textSupportSel = MobileBy.AccessibilityId("Support");
//    private final By androidIconSel = MobileBy.xpath("//*[@text='Demo app for the appium-boilerplate']/following-sibling::android.widget.TextView[2]");
//    private final By iosIconSel = MobileBy.xpath("//*[@text='Demo app for the appium-boilerplate']/following-sibling::android.widget.TextView[1]");

    private final AppiumDriver<MobileElement> driver;

    @AndroidFindBy(xpath = "//*[@text='Demo app for the appium-boilerplate']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Demo app for the appium-boilerplate'")
     private MobileElement appPurposeSel;

    @AndroidFindBy(xpath = "//*[@text='Support']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Support'")
    private MobileElement textSupportSel;

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
    }

    public void verifyAppPurposeDisplayed(){
        Assert.assertTrue(appPurposeSel.isDisplayed());

    }

//    public HomePage verifyTextSupportDisplayed(){
//scrollToElement(textSupportLoc);
//        Assert.assertTrue(isElementDisplayed(textSupportLoc));
//        return this;
//    }

//    public HomePage verifyAndroidIconDisplayed(){
//        Assert.assertTrue(isElementDisplayed(androidIconSel));
//        return this;
//    }
//    public void verifyIosIconDisplayed(){
//        Assert.assertTrue(isElementDisplayed(iosIconSel));
//    }

    public BottomNavigationComponent bottomNavigationComponent(){
        return new BottomNavigationComponent(driver);
    }

    public void verifyTextSupportDisplay() {
        Assert.assertTrue(isElementDisplayed(textSupportSel));
    }

}
