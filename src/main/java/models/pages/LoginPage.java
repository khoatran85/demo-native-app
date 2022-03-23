package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.time.Duration;

public class LoginPage extends CommonMethods {
    private final AppiumDriver<MobileElement> driver;
    private final By emailTextBoxSel = MobileBy.AccessibilityId("input-email");
    private final By passwordTextBoxSel = MobileBy.AccessibilityId("input-password");
    private final By loginBtnBoxSel = MobileBy.AccessibilityId("button-LOGIN");

    @AndroidFindBy(xpath = "//*[@text='Please enter a valid email address']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Please enter a valid email address'")
    private MobileElement emailWarningMsgSel;

    @AndroidFindBy(xpath = "//*[@text='Please enter at least 8 characters']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Please enter at least 8 characters'")
    private MobileElement passwordWarningMsgSel;

    @AndroidFindBy(xpath = "//*[@text='Success']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Success'")
    private MobileElement loginSuccessPopupTitleSel;

    @AndroidFindBy(xpath = "//*[@text='OK']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'OK'")
    private MobileElement loginSuccessPopupOKBtnSel;

    @AndroidFindBy(xpath = "//*[@text='You are logged in!']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'You are logged in!'")
    private MobileElement loginSuccessPopupMsgSel;

    public LoginPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public LoginPage inputToEmailTextBox(String value) {
        sendKeyToTextBox(emailTextBoxSel, value);
        return this;
    }

    public LoginPage inputToPasswordTextBox(String value) {
        sendKeyToTextBox(passwordTextBoxSel, value);
        return this;
    }

    public void clickToLoginBtn() {
        clickToElem(loginBtnBoxSel);
    }

    public BottomNavigationComponent bottomNavigationComponent() {
        return new BottomNavigationComponent(driver);
    }

    public boolean isEmailWarningMsgDisplayed() {
        return emailWarningMsgSel.isDisplayed();
    }

    public boolean isPasswordWarningMsgDisplayed() {
        return passwordWarningMsgSel.isDisplayed();
    }

    public boolean isLoginSuccessPopupTitleDisplayed() {
        return loginSuccessPopupTitleSel.isDisplayed();
    }

    public boolean isLoginSuccessPopupMsgDisplayed() {
        return loginSuccessPopupMsgSel.isDisplayed();
    }

    public void clickOnLoginSuccessOKButton() {
        loginSuccessPopupOKBtnSel.click();
    }

}
