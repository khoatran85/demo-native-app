package test;

import Base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.SwipePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Swipe extends BaseTest {
    private AppiumDriver<MobileElement> driver;

    String[] cardTitle = {"FULLY OPEN SOURCE", "GREAT COMMUNITY", "JS.FOUNDATION", "SUPPORT VIDEOS", "EXTENDABLE", "COMPATIBLE"};
    private SwipePage swipePage;


    @BeforeClass
    public void beforeMethod() {
        driver = getDriver();
        swipePage = new SwipePage(driver);
        swipePage.bottomNavigationComponent().clickOnSwipeLabel();
    }

    @Test(description = "User can swipe and texts are display correctly")
    public void swipe_001() {

        swipePage.verifyCardTitleDisplayCorrectlyAfterSwipe(cardTitle);
    }

    @Test(description = "Swipe verically to see the icon at the end")
    public void swipe_002() {
        swipePage.swipeUntilSeeLastIcon();
    }

}
