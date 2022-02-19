package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CommonMethods {
    private final AppiumDriver<MobileElement> driver;

    public CommonMethods(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public MobileElement findElement(String locator){
       return driver.findElementByXPath(locator);
    }

    public MobileElement findElement(By by){
        return driver.findElement(by);
    }
    public List<MobileElement> findElements(By by){
        return driver.findElements(by);
    }

    public boolean isElementDisplayed(String locator){
        return findElement(locator).isDisplayed();
    }

    public boolean isElementDisplayed(By by){
        return findElement(by).isDisplayed();
    }
    public void sendKeyToTextBox(By by, String value){
        waitForElementDisplayed(by);
        findElement(by).clear();
        findElement(by).sendKeys(value);
    }

    public void clickToElem(By by){
        waitForElementDisplayed(by);
        findElement(by).click();
    }

    public void waitForElementDisplayed(By by){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public String getElementText(By by){
        waitForElementDisplayed(by);
        return findElement(by).getText();
    }
}
