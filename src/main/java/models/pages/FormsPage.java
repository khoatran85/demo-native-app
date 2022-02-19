package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
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
    private final By dropdownPopupSel = MobileBy.xpath("//*[@resource-id='com.wdiodemoapp:id/select_dialog_listview']");
    private final By dropdownListSel = MobileBy.xpath("//*[@text='Select an item...']/following-sibling::android.widget.CheckedTextView");
    private final By selectedDropdownValueSel = MobileBy.xpath("//*[@resource-id='icon_container']/preceding-sibling::android.widget.EditText");


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
        for (MobileElement element : dropdownList) {
            dropdownValues.add(element.getText());
        }
//        clickToElem(MobileBy.xpath("//*[@text='Select an item...']"));

        Select select = new Select(findElement(dropdownListSel));
        select.selectByValue("Appium is awesome");
//        System.out.println(select.getOptions());
//        for (String dropdownValue : dropdownValues) {
//            select.selectByValue(dropdownValue);
//            Assert.assertEquals(getElementText(selectedDropdownValueSel), dropdownValue);
//        }


    }
}