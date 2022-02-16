package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.FormsPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Forms extends BaseTest{
    AppiumDriver<MobileElement> driver;
    FormsPage formsPage;
    @BeforeClass
    public void beforeClass(){
    driver = getDriver();
    formsPage = new FormsPage(driver);
        formsPage.bottomNavigationComponent().clickOnFormLabel();

    }

//    @Test(description = "What user input can be displayed")
    public void Form_001(){
        String value = "Hello Friend!";
    formsPage.inputTextToInputField(value).verifyTextResultDisplayed(value);
    }

//    @Test(description = "User can switch on/off and text displayed")
    public void Form_002(){
        formsPage.verifyTextDisplayedCorrectlyAfterSwitchOnOff();
    }

//    @Test(description = "User can switch on/off and text displayed")
    public void Form_003(){
        formsPage.verifyTextDisplayedCorrectlyAfterSwitchOnOff();
    }

    @Test
    public void Form_004(){
        formsPage.verifyDropdownSelection();
    }
}

