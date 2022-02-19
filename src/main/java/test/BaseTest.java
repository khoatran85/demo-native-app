package test;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseTest {
    protected AppiumDriver<MobileElement> driver;
    private final List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private ThreadLocal<DriverFactory> driverThread;
    private String platformName;
    private String udid;
    private String systemPort;
    private String platformVersion;



    @BeforeTest(alwaysRun = true, description = "Init all appium sessions")
    @Parameters({"udid", "systemPort", "platformName", "platformVersion"})
    public void beforeTest(String udid, String systemPort, String platformName, @Optional("platformVersion") String platformVersion) {
        System.out.println("Before Test run");
        this.platformName = platformName;
        this.udid = udid;
        this.systemPort = systemPort;
        this.platformVersion = platformVersion;
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory driverThread = new DriverFactory();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driverThread.get().quitDriver();
    }

    public AppiumDriver<MobileElement> getDriver() {
        System.out.println(platformName);
        System.out.println(udid);
        System.out.println(systemPort);
        System.out.println(platformVersion);
        System.out.println(driverThread);
        if (driver == null) {
                driver = driverThread.get().getDriver(udid, systemPort, platformName, platformVersion);
        }
//        if (driver == null) {
//            throw new RuntimeException("[ERR] Can't establish a connection to test");
//        }
        return driver;
    }

    @BeforeClass(alwaysRun=true)
    public void beforeClass(){
        driver = getDriver();
    }
//    @AfterClass(alwaysRun=true)
//    public void afterClass(){
//        driver = driverThread.get().getDriver(udid, systemPort, platformName, platformVersion);
//    }

}
