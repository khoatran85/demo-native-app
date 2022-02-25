package test;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {
    private AppiumDriver<MobileElement> driver;
    private final List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private ThreadLocal<DriverFactory> driverThread;
    private String platformName;
    private String udid;
    private String systemPort;
    private String platformVersion;


    @BeforeClass(alwaysRun = true, description = "Init all appium sessions")
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

    @AfterClass(alwaysRun = true)
    public void afterTest() {
        driverThread.get().quitDriver();
    }

    public AppiumDriver<MobileElement> getDriver() {
        if (driver == null) {
                driver = driverThread.get().getDriver(udid, systemPort, platformName, platformVersion);
        }
        if (driver == null) {
            throw new RuntimeException("[ERR] Can't establish a connection to test");
        }
        return driver;
    }

    @AfterMethod(description = "Capture Screenshot on failure")
    public void afterMethod(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){

            // 1. Get the test method name
            String testMethodName = result.getName();

            // 2. Get taken time
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            String dateTaken = y + "-" + m + "-" + d + "-" + hr + "-" + min + "-" + sec;

            // 3. Location to save
            String fileLocation =
                    System.getProperty("user.dir") + "/screenshots/" + testMethodName + "_" + dateTaken + ".png";

            // 4. save
            File screenShot = driverThread.get().getDriver().getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenShot, new File(fileLocation));

                // Get file content as InputStream to attach to allure
                Path content = Paths.get(fileLocation);
                InputStream inputStream = Files.newInputStream(content);
                Allure.addAttachment(testMethodName, inputStream);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
