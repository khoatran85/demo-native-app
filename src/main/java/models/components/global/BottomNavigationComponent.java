package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class BottomNavigationComponent {
    private final AppiumDriver<MobileElement> driver;
    private static final By homeLabelSel = MobileBy.AccessibilityId("Webview");
    private static final By webViewLabelSel = MobileBy.AccessibilityId("Webview");
    private static final By loginLabelSel = MobileBy.AccessibilityId("Login");
    private static final By formLabelSel = MobileBy.AccessibilityId("Forms");
    private static final By swipeLabelSel = MobileBy.AccessibilityId("Swipe");
    private static final By dragLabelSel = MobileBy.AccessibilityId("Drag");

    public BottomNavigationComponent(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void clickOnHomeLabel(){
        driver.findElement(homeLabelSel).click();
    }

    public void clickOnWebViewLabel(){
        driver.findElement(webViewLabelSel).click();
    }

    public void clickOnLoginLabel(){
        driver.findElement(loginLabelSel).click();
    }

    public void clickOnFormLabel(){
        driver.findElement(formLabelSel).click();
    }

    public void clickOnSwipeLabel(){
        driver.findElement(swipeLabelSel).click();
    }

    public void clickOnDragLabel(){
        driver.findElement(dragLabelSel).click();
    }


}
