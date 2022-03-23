package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonMethods;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FormsPage extends CommonMethods {
    private final AppiumDriver<MobileElement> driver;
    private final By inputTextBoxSel = MobileBy.AccessibilityId("text-input");
    private final By inputTextResultSel = MobileBy.AccessibilityId("input-text-result");
    private final By switchOnOffBtnSel = MobileBy.AccessibilityId("switch");
    private final By switchTextSel = MobileBy.AccessibilityId("switch-text");
    private final By dropdownListSel = MobileBy.xpath("//*[@text='Select an item...']/following-sibling::android.widget.CheckedTextView");
    private final By firstValueInDropdownListSel = MobileBy.xpath("//*[@text='Select an item...']/following-sibling::android.widget.CheckedTextView");
    private final By activeBtnSel = MobileBy.AccessibilityId("button-Active");
//    private final By activeSuccessPopupOKBtnSel = MobileBy.xpath("//*[@text='OK']");
    private final By activeSuccessPopupCancelBtnSel = MobileBy.xpath("//*[@text='CANCEL']");
    private final By activeSuccessPopupAskMeLaterBtnSel = MobileBy.xpath("//*[@text='ASK ME LATER']");

    @AndroidFindBy(xpath = "//*[@resource-id='icon_container']")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'text_input'")
    private MobileElement dropdownBtnSel;

    @AndroidFindBy(xpath = "//*[@resource-id='icon_container']/preceding-sibling::android.widget.EditText")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'text_input'")
    private MobileElement selectedDropdownValueSel;

    @AndroidFindBy(xpath = "//*[@text='This button is active']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'This button is active'")
    private MobileElement activeSuccessPopupMsgSel;

    @AndroidFindBy(xpath = "//*[@text='OK']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'OK'")
    private MobileElement activeSuccessPopupOKBtnSel;

    public FormsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public FormsPage inputTextToInputField(String value) {
        sendKeyToTextBox(inputTextBoxSel, value);
        return this;
    }

    public void verifyTextResultDisplayed(String value) {
        waitForElementDisplayed(inputTextBoxSel);
        Assert.assertEquals(getElementText(inputTextResultSel), value);
    }

    public BottomNavigationComponent bottomNavigationComponent() {
        return new BottomNavigationComponent(driver);
    }

    public void verifyTextDisplayedCorrectlyAfterSwitchOnOff() {
        String switchText = getElementText(switchTextSel);
        clickToElem(switchOnOffBtnSel);
        if (switchText.contains("ON")) {
            Assert.assertTrue((getElementText(switchTextSel)).contains("OFF"));
        } else {
            Assert.assertTrue((getElementText(switchTextSel)).contains("ON"));
        }
    }

    public void verifyDropdownSelectionAndroid() {
        dropdownBtnSel.click();
        List<MobileElement> dropdownList = findElements(dropdownListSel);
        List<String> dropdownValues = new ArrayList<>();

        //get all dropdown value and add to dropdownValues list
        for (MobileElement element : dropdownList) {
            dropdownValues.add(element.getText());
        }
//        System.out.println(dropdownValues);

        //Close the dropdown list
        clickToElem(firstValueInDropdownListSel);

        String actualValue = null;
        String expectedValue = null;

        for (int i = 0; i < dropdownValues.size(); i++) {
            dropdownBtnSel.click();
            List<MobileElement> dropdownList1 = findElements(dropdownListSel);
//            System.out.println("select item name = " + dropdownList1.get(i).getText());
            dropdownList1.get(i).click();
            actualValue = selectedDropdownValueSel.getText();
//            System.out.println("actualValue = " + actualValue);
            for (int j = i; j < dropdownValues.size(); j++) {
                expectedValue = dropdownValues.get(j);
//                System.out.println("expectedValue = " + dropdownValues.get(j));
                Assert.assertEquals(actualValue, expectedValue);
                if (actualValue.contains(expectedValue))
                    break;
            }
        }
    }

    public void verifyDropdownSelectionIOS() {
        dropdownBtnSel.click();
        sleepInSeconds(1l);
        //Swipe to select item
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        int xStartPoint = (width * 50) / 100;
        int xEndPoint = xStartPoint;
        int yStartPoint = (height * 90) / 100;
        int yEndPoint = (height * 85) / 100;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
        int maxSwipe = 3;
        int countSwipe = 0;
        String[] dropDownMenuList = {"webdriver.io is awesome", "Appium is awesome", "This app is awesome"};
        while (countSwipe < maxSwipe) {
            TouchAction action = new TouchAction(driver);

            for (int i = 0; i < dropDownMenuList.length; i++) {
                action.longPress(startPoint).moveTo(endPoint).release().perform();
                System.out.println(selectedDropdownValueSel.getText());
                String selectedValue = selectedDropdownValueSel.getText();
                Assert.assertEquals(selectedValue, dropDownMenuList[i]);
                System.out.println("equal = " + selectedValue.equals(dropDownMenuList[i]));
                countSwipe++;
                System.out.println(countSwipe);
            }
            if (countSwipe == maxSwipe) {
                clickOnCenterScreenIOS();
                break;
            }
        }
    }


    public void clickOnActiveBtn() {
        swipeVerticallyToElement(findElements(activeBtnSel));
        clickToElem(activeBtnSel);
    }

    public String getActiveSuccessPopupMsg() {
        return activeSuccessPopupMsgSel.getText();
    }

    public void verifyDropdownSelection() {
        String platformName = driver.getPlatformName();
        System.out.println(platformName);
        if(platformName.equalsIgnoreCase("ios"))
            verifyDropdownSelectionIOS();
        if(platformName.equalsIgnoreCase("android"))
            verifyDropdownSelectionAndroid();
    }
    public void clickOnActiveSuccessPopupOKBtn(){
        activeSuccessPopupOKBtnSel.click();
    }

}