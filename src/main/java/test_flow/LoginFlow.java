package test_flow;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import org.testng.Assert;
import test.LoginCred;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFlow  {
    private final AppiumDriver<MobileElement> driver;
    LoginCred loginData;

    public LoginFlow(AppiumDriver<MobileElement> driver, LoginCred loginData) {
        this.driver = driver;
        this.loginData = loginData;
    }

    public LoginFlow login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.bottomNavigationComponent().clickOnLoginLabel();
        loginPage.inputToEmailTextBox(loginData.getEmail()).inputToPasswordTextBox(loginData.getPassword()).clickToLoginBtn();
        return this;
    }

    public void verifyLogin() {
        if(!isEmailValid(loginData.getEmail()))
            verifyEmailWarningMsgDisplayed();

        if(!isPasswordvalid(loginData.getPassword()))
            verifyPasswordWarningMsgDisplayed();

        if(isEmailValid(loginData.getEmail()) && isPasswordvalid(loginData.getPassword()))
            verifyLoginSuccess();

    }

    private void verifyLoginSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginSuccessPopupTitleDisplayed() && loginPage.isLoginSuccessPopupMsgDisplayed());
        loginPage.clickOnLoginSuccessOKButton();
    }

    private void verifyPasswordWarningMsgDisplayed() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPasswordWarningMsgDisplayed());
    }

    private void verifyEmailWarningMsgDisplayed() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isEmailWarningMsgDisplayed());
    }

    private boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isPasswordvalid(String password) {
        String expression = "^.{8,20}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
