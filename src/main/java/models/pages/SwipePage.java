package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonMethods;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;


public class SwipePage extends CommonMethods {
    private final AppiumDriver<MobileElement> driver;
    //    private By mainViewCardTitle = MobileBy.xpath("(//*[@content-desc='Carousel']//android.view.ViewGroup//android.widget.TextView[1])[last()-1]");
//    private By webDriverLogo = MobileBy.xpath("//*[@content-desc='WebdriverIO logo']");

    @AndroidFindBy(xpath = "//*[@content-desc='card']//android.view.ViewGroup[@content-desc='slideTextContainer']/android.widget.TextView[1]")
    @iOSXCUITFindBy(xpath = "(//*[@name='card']//XCUIElementTypeOther[@name='slideTextContainer']/XCUIElementTypeStaticText[1])[last()-1]")
//    @iOSXCUITFindBy(xpath = "//*[@name='card']//XCUIElementTypeOther[@name='slideTextContainer']/XCUIElementTypeStaticText[1]")
    private MobileElement mainViewCardTitle;

    @iOSXCUITFindBy(xpath = "(//*[@name='card']//XCUIElementTypeOther[@name='slideTextContainer']/XCUIElementTypeStaticText[1])[last()]")
    private MobileElement lastItemCarousel;

    @AndroidFindBy(xpath = "//*[@text='You found me!!!']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'You found me!!!'")
    private List<MobileElement> bottomText;

    public SwipePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void verifyCardTitleDisplayCorrectlyAfterSwipe(String[] cardTitle) {
        int totalCardItems = (int) Arrays.stream(cardTitle).count();
        for (int i = 0; i < totalCardItems; i++) {
            String currentCardTitle = cardTitle[i];
            sleepInSeconds(1L);
                if (i == 5 && driver.getPlatformName().contains("ios")) {
                    System.out.println(lastItemCarousel.getText());
                    break;
                }
            System.out.println(mainViewCardTitle.getText());
            Assert.assertEquals(mainViewCardTitle.getText(), currentCardTitle);
            swipeHorizontally();
        }
    }

    public BottomNavigationComponent bottomNavigationComponent() {
        return new BottomNavigationComponent(driver);
    }

    public void swipeUntilSeeLastIcon() {
        swipeVerticallyToElement(bottomText);
    }
}
