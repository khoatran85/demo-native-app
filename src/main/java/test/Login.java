package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_flow.LoginFlow;
import utils.DataObjectBuilder;

public class Login extends BaseTest{
    AppiumDriver<MobileElement> driver;

    LoginPage loginPage;
    @BeforeClass
    public void beforeClass(){
        driver = getDriver();
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "loginData")
    public void login(LoginCred loginData){
        //Make sure "missing email and password" displayed
        LoginFlow loginFlow = new LoginFlow(driver, loginData);
        loginFlow.login().verifyLogin();
    }

    @DataProvider
    public LoginCred[] loginData(){
        String filePath = "/src/main/resources/test_data/LoginCred.json";
        return DataObjectBuilder.buildJsonDataObject(filePath, LoginCred[].class);
    }


}
