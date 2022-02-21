package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class FormsPage extends CommonMethods {
    private final AppiumDriver<MobileElement> driver;
    private final By inputTextBoxSel = MobileBy.AccessibilityId("text-input");
    private final By inputTextResultSel = MobileBy.AccessibilityId("input-text-result");
    private final By switchOnOffBtnSel = MobileBy.AccessibilityId("switch");
    private final By switchTextSel = MobileBy.AccessibilityId("switch-text");
    private final By dropdownBtnSel = MobileBy.xpath("//*[@resource-id='icon_container']");
    private final By dropdownListSel = MobileBy.xpath("//*[@text='Select an item...']/following-sibling::android.widget.CheckedTextView");
    private final By firstValueInDropdownListSel = MobileBy.xpath("//*[@text='Select an item...']/following-sibling::android.widget.CheckedTextView");
    private final By selectedDropdownValueSel = MobileBy.xpath("//*[@resource-id='icon_container']/preceding-sibling::android.widget.EditText");
    private final By activeBtnSel = MobileBy.AccessibilityId("button-Active");
    private final By activeSuccessPopupMsgSel = MobileBy.xpath("//*[@text='This button is active']");
    private final By activeSuccessPopupOKBtnSel = MobileBy.xpath("//*[@text='OK']");
    private final By activeSuccessPopupCancelBtnSel = MobileBy.xpath("//*[@text='CANCEL']");
    private final By activeSuccessPopupAskMeLaterBtnSel = MobileBy.xpath("//*[@text='ASK ME LATER']");


    public FormsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
    }

    public FormsPage inputTextToInputField(String value) {
        sendKeyToTextBox(inputTextBoxSel, value);
        return this;
    }

    public void verifyTextResultDisplayed(String value) {
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

    public void verifyDropdownSelection() {
        clickToElem(dropdownBtnSel);
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
            clickToElem(dropdownBtnSel);
            List<MobileElement> dropdownList1 = findElements(dropdownListSel);
//            System.out.println("select item name = " + dropdownList1.get(i).getText());
            dropdownList1.get(i).click();
            actualValue = getElementText(selectedDropdownValueSel);
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

    public void clickOnActiveBtn(){
        swipeVerticallyToElement(activeBtnSel);
        clickToElem(activeBtnSel);
    }

    public String getActiveSuccessPopupMsg(){
        return getElementText(activeSuccessPopupMsgSel);
    }

}