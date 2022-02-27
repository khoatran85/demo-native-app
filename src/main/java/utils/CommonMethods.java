package utils;

import Base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CommonMethods{
    private final AppiumDriver<MobileElement> driver;

    public CommonMethods(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

//    public MobileElement findElement(String locator){
//       return driver.findElementByXPath(locator);
//    }

    public MobileElement findElement(By by){
        return driver.findElement(by);
    }

    public List<MobileElement> findElements(By by){
        return driver.findElements(by);
    }


    public boolean isElementDisplayed(By by){
        return findElement(by).isDisplayed();
    }
    public void sendKeyToTextBox(By by, String value){
//        waitForElementDisplayed(by);
        findElement(by).clear();
        findElement(by).sendKeys(value);
    }

    public void clickToElem(By by){
//        waitForElementDisplayed(by);
        findElement(by).click();
    }

    public void waitForElementDisplayed(By by){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public String getElementText(By by){
//        waitForElementDisplayed(by);
        return findElement(by).getText();
    }

    public void swipeVerticallyToElement(By by){
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        int xStartPoint = (width * 50) / 100;
        int xEndPoint = xStartPoint;
        int yStartPoint = (height * 30) / 100;
        int yEndPoint = (height * 10) / 100;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint,yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint,yEndPoint);
        while (findElements(by).size() ==0) {
        TouchAction action = new TouchAction(driver);
        action.longPress(startPoint).moveTo(endPoint).release().perform();
            List<MobileElement> elems = findElements(by);
            if(elems.size() > 0)
                break;
        }
    }


    public void swipeHorizontally(){
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        int xStartPoint = (width * 70) / 100;
        int xEndPoint = (width * 20) / 100;
        int yStartPoint = (height * 70) / 100;
        int yEndPoint = yStartPoint;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint,yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint,yEndPoint);
        TouchAction action = new TouchAction(driver);
        action.longPress(startPoint).moveTo(endPoint).release().perform();

    }

    public void sleepInSeconds(Long timeOut){
        try {
            Thread.sleep(timeOut*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
