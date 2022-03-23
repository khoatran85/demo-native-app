package Base;

import driver.MobileCapabilityTypeEx;
import driver.PlatformType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaseTest0 {
    //    protected static AppiumDriver<MobileElement> driver;
//    List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
//    protected static ThreadLocal<DriverFactory> driver = new ThreadLocal<>();
    protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
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
//        command.addArgument("appium -p 7000 --nodeconfig selenium_grid/node_8000_config.json --allow-insecure chromedriver_autodownload");
////        command.addArgument("\"java -cp \\\"selenium_grid\\\\selenium-server-standalone-3.141.59.jar;selenium_grid\\\\selenium-grid-custom-matcher-3.141.59.jar\\\" org.openqa.grid.selenium.GridLauncherV3 -role hub -hubConfig selenium_grid\\\\hubConfig.json  \"");
////        Runtime.getRuntime().exec("\"java -cp \\\"selenium_grid\\\\selenium-server-standalone-3.141.59.jar;selenium_grid\\\\selenium-grid-custom-matcher-3.141.59.jar\\\" org.openqa.grid.selenium.GridLauncherV3 -role hub -hubConfig selenium_grid\\\\hubConfig.json  \"");
////        Runtime.getRuntime().exec("appium -p 6000 --nodeconfig selenium_grid\\node_6000_config.json --allow-insecure chromedriver_autodownload");
////        Runtime.getRuntime().exec("appium -p 7000 --nodeconfig selenium_grid\\node_8000_config.json --allow-insecure chromedriver_autodownload");
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
        initDriver(udid, systemPort, platformName, platformVersion);
    }
//
//    @BeforeClass
//    public void beforeClass() {
//        System.out.println("Before Class = " + driverThread);
//    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driver.get().quit();
    }

    public AppiumDriver getDriver() {
        System.out.println("driver threadpool in getdriver = " + driver.get());

        if (driver.get() == null) {
            System.out.println("get driver = " + driver.get());
            initDriver(udid, systemPort, platformName, platformVersion);
        }
        if (driver == null) {
            throw new RuntimeException("[ERR] Can't establish a connection to test");
        }
        return driver.get();
    }

    public void setDriver(AppiumDriver<MobileElement> driver) {
        this.driver.set(driver);
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
            File screenShot = getDriver().getScreenshotAs(OutputType.FILE);

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


    private void initDriver(String udid, String systemPort, String platformName, String platformVersion) {
        try {
            PlatformType.valueOf(platformName.toLowerCase());
            System.out.println("platform = " + platformName);
        } catch (Exception e) {
            throw new IllegalArgumentException("We don't support " + platformName);
        }
        boolean isAndroid = platformName.equalsIgnoreCase("android");
        System.out.println("platform = " + isAndroid);
        try {
            // Specify capabilities
            DesiredCapabilities desiredCaps = new DesiredCapabilities();
            desiredCaps.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, platformName);

            if (isAndroid) {
                desiredCaps.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
                desiredCaps.setCapability(MobileCapabilityTypeEx.UDID, udid);
                desiredCaps.setCapability(MobileCapabilityTypeEx.SYSTEM_PORT, Integer.parseInt(systemPort));
                desiredCaps.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
                desiredCaps.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
            } else {
                desiredCaps.setCapability(MobileCapabilityTypeEx.WDA_LOCAL_PORT, Integer.parseInt(systemPort));
                desiredCaps.setCapability(MobileCapabilityTypeEx.DEVICE_NAME, udid);
                desiredCaps.setCapability(MobileCapabilityTypeEx.PLATFORM_VERSION, platformVersion);
                desiredCaps.setCapability(MobileCapabilityTypeEx.BUNDLE_ID, "org.wdioNativeDemoApp");
                desiredCaps.setCapability(MobileCapabilityTypeEx.NO_RESET, true);
            }

            URL remoteServer = new URL("http://localhost:4444/wd/hub");
//            String hub = System.getProperty("hub") != null ? System.getProperty("hub") : System.getenv("hub");
//            System.out.println("hub =" + hub);
//            if(hub != null){
//                remoteServer = new URL("http://localhost:4444/wd/hub");}
//            else {
//                remoteServer = new URL("http://localhost:4723/wd/hub");
//            }
//            System.out.println("remoreServer = " + remoteServer);
            driver.set(new AppiumDriver(remoteServer, desiredCaps));
            getDriver().manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
