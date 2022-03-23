package test;

import Base.BaseTest;
import io.appium.java_client.AppiumDriver;
import models.pages.FormsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Forms extends BaseTest {
    FormsPage formsPage;
    private AppiumDriver driver;

    @BeforeClass
    public void beforeMethod(){
       driver = getDriver();
    formsPage = new FormsPage(driver);
    formsPage.bottomNavigationComponent().clickOnFormLabel();
    }

    @Test(description = "What user input can be displayed")
    public void form_001(){
        String value = "Hello Friend!";
    formsPage.inputTextToInputField(value).verifyTextResultDisplayed(value);
    }

    @Test(description = "User can switch on/off and text displayed")
    public void form_002(){
        formsPage.verifyTextDisplayedCorrectlyAfterSwitchOnOff();
    }

    @Test(description = "User can switch on/off and text displayed")
    public void form_003(){
        formsPage.verifyTextDisplayedCorrectlyAfterSwitchOnOff();
    }

    @Test(description = "user can select dropdown webdriverio/appium/this app is awesome")
    public void form_004(){
        formsPage.verifyDropdownSelection();
    }

    @Test(description = "Active/inavtive button works properly")
    public void form_005(){
        //Verify Active button works properly
        formsPage.clickOnActiveBtn();
        Assert.assertEquals(formsPage.getActiveSuccessPopupMsg(), "This button is active");

        //close the popup
        formsPage.clickOnActiveSuccessPopupOKBtn();

        //Verify inactive button is unable to be clicked on.

    }
}



