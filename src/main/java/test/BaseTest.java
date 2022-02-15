package test;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseTest {
    private AppiumDriver<MobileElement> driver;
    private final List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private ThreadLocal<DriverFactory> driverThread;
    private String platformName;
    private String udid;
    private String systemPort;

    @BeforeTest(alwaysRun = true)
    @Parameters({"platformName", "udid", "systemPort"})
    public void beforeTest(String platformName, String udid, String systemPort) {
        System.out.println("Before Test run");
        this.platformName = platformName;
        this.udid = udid;
        this.systemPort = systemPort;
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
        if (driver == null) {
            driver = driverThread.get().getDriver(platformName, udid, systemPort);
        }
        if (driver == null) {
            throw new RuntimeException("[ERR] Can't establish a connection to test");
        }
        return driver;
    }

}
