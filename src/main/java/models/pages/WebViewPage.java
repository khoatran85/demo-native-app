package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.CommonMethods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebViewPage extends CommonMethods {
    private final AppiumDriver<MobileElement> driver;
    private By menuBtnSel = MobileBy.xpath("//button[@class='navbar__toggle clean-btn']");
    private By logoSel = MobileBy.xpath("//div[@class='navbar-sidebar__brand']//div[@class='navbar__logo']");
    private By menusTextSel = MobileBy.xpath("//li[@class='menu__list-item'][last()]/preceding-sibling::li/a");


    public WebViewPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
    }

    public WebViewPage switchToWebView() {
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            if (contextName.contains("WEBVIEW")) {
                driver.context(contextName);
            }
        }
        return this;
    }

    public void clickOnMenuBtn() {
        clickToElem(menuBtnSel);
        Assert.assertTrue(isElementDisplayed(logoSel));
    }

    public void verifyMenuTextsAndLinksCorrect() {
        Map<String, String> menusWithLinks = new HashMap<>();
        menusWithLinks.put("Docs", "/docs/gettingstarted");
        menusWithLinks.put("API", "/docs/api");
        menusWithLinks.put("Blog", "/blog");
        menusWithLinks.put("Contribute", "/docs/contribute");
        menusWithLinks.put("Community", "/community/support");
        menusWithLinks.put("v7", "/versions");

//        for (Map.Entry<String, String> entry : menusWithLinks.entrySet()) {
//            System.out.println(entry.getKey() + " = " + entry.getValue());
////            System.out.println(entry.getValue());
//        }
//        List<MobileElement> elements = findElements(menusTextSel);
//
//        for(MobileElement element : elements) {
//            String menuName = element.getText();
//            String menuLink = element.getAttribute("href");
//            System.out.println(menuName + " = " + menuLink);
//        }

//        List<MobileElement> elements = findElements(menusTextSel);
//        for (MobileElement element : elements) {
//            String menuName = element.getText();
//            String menuLink = element.getAttribute("href");


        switch (menuName){
            case"Docs":
                Assert.assertEquals

}
}


public BottomNavigationComponent bottomNavigationComponent(){
        return new BottomNavigationComponent(driver);
        }


        }
