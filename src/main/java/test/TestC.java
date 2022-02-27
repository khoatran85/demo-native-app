package test;

import Base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestC extends BaseTest {
//    private AppiumDriver<MobileElement> driver;
//    @BeforeClass
//    public void beforeClass(){
//
//        driver = getDriver();
//    }

    @Test
    public void testC(){
        System.out.println("driver testC = " + driver);
    }
}
