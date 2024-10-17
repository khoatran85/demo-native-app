package test;

import Base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import models.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.annotations.*;


public class Login extends BaseTest {
    LoginPage loginPage;
    private AppiumDriver driver;
    private final By userName = MobileBy.xpath("//android.widget.EditText[@text='Mã nhân viên / Email']");
    private final By password = MobileBy.xpath("//android.widget.EditText[@text='Mật khẩu']");
    private final By loginBtn = MobileBy.xpath("//android.widget.Button[@content-desc='ĐĂNG NHẬP']");

       private final By newBillBtn = MobileBy.xpath("//android.view.View[@content-desc='Môi trường DEVELOPMENT']/following-sibling:: android.view.View/android.widget.Button[1]");
    private final By searchBox = MobileBy.xpath("(//android.view.View[@content-desc='Môi trường DEVELOPMENT']/following-sibling:: android.view.View//android.widget.EditText)[1]");
    private final By cashBtn = MobileBy.xpath("//android.widget.RadioButton");
    private final By payBtn = MobileBy.xpath("//android.widget.Button[@content-desc='Thanh toán (F9)']");
    private final By productLineGiftBtn = MobileBy.xpath("//android.widget.Button[@content-desc='Thanh toán (F9)']");

    @BeforeClass
    public void beforeClass() {
        driver = getDriver();
        loginPage = new LoginPage(driver);
    }


    @Test
    public void login() {
        loginPage.clickToElem(userName);
        loginPage.sendKeyToTextBox(userName, "KF001273");
        loginPage.clickToElem(password);
        loginPage.sendKeyToTextBox(password, "123456");
        loginPage.clickToElem(loginBtn);

        loginPage.waitForElementDisplayed(newBillBtn);
        loginPage.clickToElem(newBillBtn);

        loginPage.clickToElem(searchBox);
        loginPage.sendKeyToTextBox(searchBox, "QAZ103 ");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Chọn sp ở trn
        loginPage.clickOnPruductList();

        loginPage.clickToElem(cashBtn);

        loginPage.clickToElem(payBtn);


    }



}
