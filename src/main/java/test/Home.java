package test;

import Base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Home extends BaseTest {
    HomePage homePage;
    private AppiumDriver driver;

    @BeforeClass
    public void beforeMethod(){
       driver = getDriver();
       getDriver();
        homePage = new HomePage(driver);
    }


    @Test
    public void homePage_001() {
        homePage.bottomNavigationComponent().clickOnHomeLabel();
        homePage.verifyAppPurposeDisplayed();

    }

    @Test
    public void homePage_002() {
//        homePage.verifyTextSupportDisplayed().verifyAndroidIconDisplayed().verifyIosIconDisplayed();
        homePage.verifyTextSupportDisplay();
    }


}
