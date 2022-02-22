package test;

import models.pages.WebViewPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebView extends BaseTest {
    WebViewPage webViewPage;

    @BeforeMethod
    public void beforeMethod(){
        webViewPage = new WebViewPage(driver);
        webViewPage.bottomNavigationComponent().clickOnWebViewLabel();
    }
    @Test(description = "make sure the menu text and hyperlink displayed correctly")
    public void webView(){
        webViewPage.switchToWebView().clickOnMenuBtn().verifyMenuTextsAndLinksCorrect();
    }

}
