package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class CommonMethods {
    private final AppiumDriver<MobileElement> driver;

    public CommonMethods(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public MobileElement findElement(String locator){
       return driver.findElementByXPath(locator);
    }

    public boolean isElementDisplayed(String locator){
        return findElement(locator).isDisplayed();
    }
}
