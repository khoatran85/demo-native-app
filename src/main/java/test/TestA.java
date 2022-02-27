package test;

import Base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestA extends BaseTest {
//    private AppiumDriver<MobileElement> driver;
//    @BeforeClass
//    public void beforeClass(){
//
//    driver = getDriver();
//    }

    @Test
    public void testA(){
        System.out.println("driver testA = " + driver);
    }
}
