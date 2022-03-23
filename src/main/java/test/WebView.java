package test;

import Base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.WebViewPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebView extends BaseTest {
    WebViewPage webViewPage;
    protected AppiumDriver driver;

    @BeforeClass
    public void beforeMethod(){
      driver = getDriver();
        webViewPage = new WebViewPage(driver);
        webViewPage.bottomNavigationComponent().clickOnWebViewLabel();
    }
    @Test(description = "make sure the menu text and hyperlink displayed correctly")
    public void webView(){
//        webViewPage.switchToWebView().clickOnMenuBtn().verifyMenuTextsAndLinksCorrect();
        webViewPage.clickOnMenuBtn().verifyMenuTextsAndLinksCorrect();
        webViewPage.switchToNativeApp();
    }

}
