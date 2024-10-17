package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CommonMethods {
    private final AppiumDriver<MobileElement> driver;

    public CommonMethods(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

//    public MobileElement findElement(String locator){
//       return driver.findElementByXPath(locator);
//    }


    public MobileElement findElement(By by) {
        return driver.findElement(by);
    }

    public List<MobileElement> findElements(By by) {
        return driver.findElements(by);
    }


    public boolean isElementDisplayed(MobileElement element) {
        return element.isDisplayed();
    }

    public void sendKeyToTextBox(By by, String value) {
        waitForElementDisplayed(by);
        findElement(by).clear();
        findElement(by).sendKeys(value);
    }
//    public void sendKeyboardToTextBox() {
////        waitForElementDisplayed(by);
////        findElement(by).clear();
//        driver.pressKeyCode(66);
//
//    }



    public void clịckByJS(By by) {
//        waitForElementDisplayed(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", findElement(by));
    }

    public void clickToElem(By by) {
        waitForElementIsClickable(by);
        findElement(by).click();
    }

    public void waitForElementDisplayed(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementIsClickable(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }


    public String getElementText(By by) {
//        waitForElementDisplayed(by);
        return findElement(by).getText();
    }

    public void swipeVerticallyToElement(List<MobileElement> elements) {
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        int xStartPoint = (width * 50) / 100;
        int xEndPoint = xStartPoint;
        int yStartPoint = (height * 50) / 100;
        int yEndPoint = (height * 10) / 100;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
        while (elements.size() == 0) {
            TouchAction action = new TouchAction(driver);
            action.press(startPoint).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(endPoint).release().perform();
            List<MobileElement> elems = elements;
            if (elems.size() > 0)
                break;
        }
    }


    public void swipeHorizontally() {
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        int xStartPoint = (width * 70) / 100;
        int xEndPoint = (width * 20) / 100;
        int yStartPoint = (height * 70) / 100;
        int yEndPoint = yStartPoint;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
        TouchAction action = new TouchAction(driver);
        action.longPress(startPoint).moveTo(endPoint).release().perform();

    }

    public void sleepInSeconds(Long timeOut) {
        try {
            Thread.sleep(timeOut * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickOnCenterScreenIOS() {
        String platformName = driver.getPlatformName();
        if (platformName.equalsIgnoreCase("ios")) {
            int width = driver.manage().window().getSize().getWidth();
            int height = driver.manage().window().getSize().getHeight();
            int xStartPoint = (width * 50) / 100;
            int yStartPoint = xStartPoint;
            PointOption touchPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            TouchAction action = new TouchAction(driver);
            action.press(touchPoint).release().perform();
        }

    }

    public void clickOnPruductList() {
        String platformName = driver.getPlatformName();
        if (platformName.equalsIgnoreCase("android")) {
            int width = driver.manage().window().getSize().getWidth();
            int height = driver.manage().window().getSize().getHeight();
            int xStartPoint = (width * 20) / 100;
            int yStartPoint = (height * 10) / 100;
            PointOption touchPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            TouchAction action = new TouchAction(driver);
            action.press(touchPoint).release().perform();
        }

    }
}