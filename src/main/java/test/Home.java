package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Home extends BaseTest {
    AppiumDriver<MobileElement> driver;
    HomePage homePage;
    @BeforeClass
    public void beforeClass(){
        driver = getDriver();
        homePage = new HomePage(driver);
    }


    @Test
    public void homePage_001() {
        homePage.verifyAppPurposeDisplayed();
    }

    @Test
    public void homePage_002() {
        homePage.verifyTextSupportDisplayed();
    }

}
