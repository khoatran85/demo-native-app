package test;

import Base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestB extends BaseTest {
//    private AppiumDriver<MobileElement> driver;
//    @BeforeClass
//    public void beforeClass(){
//
//        driver = getDriver();
//    }

    @Test
    public void testB(){
        System.out.println("driver testB = " + driver);
    }
}
