package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.CommonMethods;
import java.util.Arrays;


public class SwipePage extends CommonMethods {
    private final AppiumDriver<MobileElement> driver;
    private By mainViewCardTitle = MobileBy.xpath("(//*[@content-desc='Carousel']//android.view.ViewGroup//android.widget.TextView[1])[last()-1]");
    private By webDriverLogo = MobileBy.xpath("//*[@content-desc='WebdriverIO logo']");


    public SwipePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
    }

    public void verifyCardTitleDisplayCorrectlyAfterSwipe(String[] cardTitle){
        int totalCardItems = (int) Arrays.stream(cardTitle).count();
        for (int i = 0; i < totalCardItems; i++){
            String currentCardTitle = cardTitle[i];
            System.out.println(getElementText(mainViewCardTitle));
            Assert.assertEquals(getElementText(mainViewCardTitle), currentCardTitle );
            swipeHorizontally();
        }
    }

    public BottomNavigationComponent bottomNavigationComponent(){
        return new BottomNavigationComponent(driver);
    }

    public void swipeUntilSeeLastIcon() {
        swipeVerticallyToElement(webDriverLogo);
    }
}
