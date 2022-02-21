package test;

import models.pages.SwipePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Swipe extends BaseTest{

    String[] cardTitle = {"FULLY OPEN SOURCE", "GREAT COMMUNITY", "JS.FOUNDATION", "SUPPORT VIDEOS", "EXTENDABLE", "COMPATIBLE"};
    private SwipePage swipePage;

    @BeforeMethod
    public void beforeMethod(){
        swipePage = new SwipePage(driver);
    }

    @Test(description = "User can swipe and texts are display correctly")
    public void swipe_001(){
        swipePage.bottomNavigationComponent().clickOnSwipeLabel();
        swipePage.verifyCardTitleDisplayCorrectlyAfterSwipe(cardTitle);
    }

    @Test(description = "Swipe verically to see the icon at the end")
    public void swipe_002(){
        swipePage.swipeUntilSeeLastIcon();
    }

}