package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;
import test.BaseTest;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {
    private final AppiumDriver<MobileElement> driver;
    private final By emailTextBoxSel = MobileBy.AccessibilityId("input-email");
    private final By passwordTextBoxSel = MobileBy.AccessibilityId("input-password");
    private final By loginBtnBoxSel = MobileBy.AccessibilityId("button-LOGIN");
    private final By emailWarningMsgSel = MobileBy.xpath("//*[@text='Please enter a valid email address']");
    private final By passwordWarningMsgSel = MobileBy.xpath("//*[@text='Please enter at least 8 characters']");
    private final By loginSuccessPopupTitleSel = MobileBy.xpath("//*[@text='Success']");
    private final By loginSuccessPopupMsgSel = MobileBy.xpath("//*[@text='You are logged in!']");
    private final By loginSuccessPopupOKBtnSel = MobileBy.xpath("//*[@text='OK']");



    public LoginPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
    }

    public LoginPage inputToEmailTextBox(String value) {
        sendKeyToTextBox(emailTextBoxSel, value);
        return this;
    }

    public LoginPage inputToPasswordTextBox(String value) {
        sendKeyToTextBox(passwordTextBoxSel, value);
        return this;
    }

    public void clickToLoginBtn(){
        clickToElem(loginBtnBoxSel);
    }

    public BottomNavigationComponent bottomNavigationComponent(){
        return new BottomNavigationComponent(driver);
    }

    public boolean isEmailWarningMsgDisplayed(){
        return findElement(emailWarningMsgSel).isDisplayed();
    }
   public boolean isPasswordWarningMsgDisplayed(){
        return findElement(passwordWarningMsgSel).isDisplayed();
    }

    public boolean isLoginSuccessPopupTitleDisplayed(){
        return findElement(loginSuccessPopupTitleSel).isDisplayed();
    }

    public boolean isLoginSuccessPopupMsgDisplayed(){
        return findElement(loginSuccessPopupMsgSel).isDisplayed();
    }

    public void clickOnLoginSuccessOKButton(){
        clickToElem(loginSuccessPopupOKBtnSel);
    }

}
