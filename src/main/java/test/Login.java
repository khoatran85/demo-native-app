package test;

import Base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import org.testng.annotations.*;
import test_flow.LoginFlow;
import utils.DataObjectBuilder;

public class Login extends BaseTest {
    LoginPage loginPage;
//    AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void beforeClass() {
//        driver = getDriver();
        getDriver();
        loginPage = new LoginPage(driver);
    }


    @Test(dataProvider = "loginData")
    public void login(LoginCred loginData) {

        //Make sure "missing email and password" displayed
        LoginFlow loginFlow = new LoginFlow(driver, loginData);
        loginFlow.login().verifyLogin();
    }

    @DataProvider
    public LoginCred[] loginData() {
        String filePath = "/src/main/resources/test_data/LoginCred.json";
        return DataObjectBuilder.buildJsonDataObject(filePath, LoginCred[].class);
    }


}
