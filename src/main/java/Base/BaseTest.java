package Base;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import utils.CommonMethods;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {
    protected static AppiumDriver<MobileElement> driver;
    List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    protected static ThreadLocal<DriverFactory> driverThread;
    private String platformName;
    private String udid;
    private String systemPort;
    private String platformVersion;
//
//    @BeforeSuite(alwaysRun = true)
//    public void beforeSuite() throws IOException, InterruptedException {
//        CommandLine command = new CommandLine("cmd");
//        command.addArgument("java -jar /Users/dd/Selenium/selenium-server-standalone-2.53.1.jar -role hub http://127.0.0.1:4444/grid ");
//        command.addArgument("appium -p 6000 --nodeconfig selenium_grid/node_6000_config.json --allow-insecure chromedriver_autodownload");
//        command.addArgument("appium -p 7000 --nodeconfig selenium_grid/node_7000_config.json --allow-insecure chromedriver_autodownload");
////        command.addArgument("\"java -cp \\\"selenium_grid\\\\selenium-server-standalone-3.141.59.jar;selenium_grid\\\\selenium-grid-custom-matcher-3.141.59.jar\\\" org.openqa.grid.selenium.GridLauncherV3 -role hub -hubConfig selenium_grid\\\\hubConfig.json  \"");
////        Runtime.getRuntime().exec("\"java -cp \\\"selenium_grid\\\\selenium-server-standalone-3.141.59.jar;selenium_grid\\\\selenium-grid-custom-matcher-3.141.59.jar\\\" org.openqa.grid.selenium.GridLauncherV3 -role hub -hubConfig selenium_grid\\\\hubConfig.json  \"");
////        Runtime.getRuntime().exec("appium -p 6000 --nodeconfig selenium_grid\\node_6000_config.json --allow-insecure chromedriver_autodownload");
////        Runtime.getRuntime().exec("appium -p 7000 --nodeconfig selenium_grid\\node_7000_config.json --allow-insecure chromedriver_autodownload");
//        Thread.sleep(5000);
//
//    }

//@AfterSuite(alwaysRun = true)
//    public void stopServer() {
//        CommandLine command = new CommandLine("cmd");
//        command.addArgument("/c");
//        command.addArgument("Taskkill /F /IM node.exe");
//
////        try {
////            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
////            DefaultExecutor executor = new DefaultExecutor();
////            executor.setExitValue(1);
////            executor.execute(command, resultHandler);
////            Thread.sleep(5000);
////
////            System.out.println("------> Appium server stopped.");
////        } catch (IOException e) {
////            e.printStackTrace();
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//
//    }

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
            System.out.println("Before test = " + driverThread);
            return driverThread;
        });
        System.out.println("Before test = " + driverThread);
        System.out.println("driver threadpool = " + driverThreadPool);
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class = " + driverThread);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driverThread.get().quitDriver();
    }

    public AppiumDriver<MobileElement> getDriver() {
        System.out.println("driver threadpool in getdriver = " + driverThreadPool);

        if (driver == null) {
            System.out.println("get driver = " + driverThread);
            driver = driverThread.get().getDriver(udid, systemPort, platformName, platformVersion);
        }
        if (driver == null) {
            throw new RuntimeException("[ERR] Can't establish a connection to test");
        }
        return driver;
    }
//
//    @BeforeClass
//    public void beforeClass() {
//       getDriver();
//    }

    @AfterMethod(description = "Capture Screenshot on failure")
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {

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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
